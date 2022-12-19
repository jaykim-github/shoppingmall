package zerobase.shoppingmall.admin.payment.service.Impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerobase.shoppingmall.admin.payment.dto.PaymentInput;
import zerobase.shoppingmall.admin.payment.dto.entity.Payment;
import zerobase.shoppingmall.admin.payment.repository.PaymentRepository;
import zerobase.shoppingmall.admin.payment.service.PaymentService;
import zerobase.shoppingmall.cart.dto.entity.Cart;
import zerobase.shoppingmall.cart.repository.CartRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final CartRepository cartRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public String getPaymentInfo(PaymentInput paymentInput) {

        try {
            //결제 준비
            URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
            HttpURLConnection connection = (HttpURLConnection) address.openConnection(); // 서버연결
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "KakaoAK 5d569ea19c6b8b53c9342d4d65a394e6");
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            connection.setDoOutput(true);

            //카트에서 토탈 값 가져오기,상품명, 총 금액
            List<Cart> cartList = cartRepository.findAllByUserId(paymentInput.getUserId())
                .orElseThrow(() -> new RuntimeException("장바구니가 없습니다."));
            Long totalPrice = 0L;

            for (Cart cart : cartList) {
                totalPrice += cart.getPrice() * cart.getCountProduct();
            }
            Payment payment = Payment.builder()
                .price(totalPrice)
                .productName(cartList.get(0).getProductName())
                .userId(paymentInput.getUserId())
                .status(0)
                .createDate(LocalDateTime.now())
                .build();

            paymentRepository.save(payment);

            String parameter = "cid=TC0ONETIME" // 가맹점 코드
                + "&partner_order_id=" +payment.getPaymentId().toString() // 가맹점 주문번호
                + "&partner_user_id=" + payment.getUserId() // 가맹점 회원 id
                + "&item_name=" + payment.getProductName() // 상품명
                + "&quantity=1" // 상품 수량
                + "&total_amount=" + payment.getPrice().toString() // 총 금액
                + "&vat_amount=1000"  //부가세
                + "&tax_free_amount=0" // 상품 비과세 금액
                + "&approval_url=http://localhost:8080/kakaopay/pgtoken" // 결제 성공 시
                + "&fail_url=http://localhost:8080/" // 결제 실패 시
                + "&cancel_url=http://localhost:8080/"; // 결제 취소 시


            //데이터 전송
            OutputStream send = connection.getOutputStream();
            DataOutputStream dataSend = new DataOutputStream(send);
            dataSend.writeBytes(parameter);
            dataSend.close();

            int result = connection.getResponseCode();
            InputStream receive;

            if(result == 200) {
                receive = connection.getInputStream();
            }else {
                receive = connection.getErrorStream();
            }

            InputStreamReader read = new InputStreamReader(receive);
            BufferedReader change = new BufferedReader(read);

            System.out.println( change.readLine());
            return change.readLine();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public void PaymentSuccess(String tid, String pg, String userId, Long paymentId) {
        try{
            URL address = new URL("https://kapi.kakao.com/v1/payment/approve");
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "KakaoAK 5d569ea19c6b8b53c9342d4d65a394e6");
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            connection.setDoOutput(true);

            String parameter = "cid=TC0ONETIME"
                + "&tid=" + tid
                + "&partner_order_id="+paymentId
                + "&partner_user_id="+userId
                + "&pg_token=" + pg;

            OutputStream send = connection.getOutputStream();
            DataOutputStream dataSend = new DataOutputStream(send);
            dataSend.writeBytes(parameter);
            dataSend.close();

            int result = connection.getResponseCode();
            InputStream receive;

            if(result == 200) {
                receive = connection.getInputStream();

                Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("결제 내역이 없습니다."));
                payment.setStatus(1);
                paymentRepository.save(payment);
            }else {
                receive = connection.getErrorStream();
            }

            InputStreamReader read = new InputStreamReader(receive);
            BufferedReader change = new BufferedReader(read);

            System.out.println(change.readLine());

            //return change.readLine();

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
