package zerobase.shoppingmall.payment.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zerobase.shoppingmall.cart.dto.entity.Cart;
import zerobase.shoppingmall.cart.repository.CartRepository;
import zerobase.shoppingmall.exception.Impl.NotCartException;
import zerobase.shoppingmall.exception.Impl.NotPaymentHistoryException;
import zerobase.shoppingmall.payment.dto.PaymentInput;
import zerobase.shoppingmall.payment.dto.PaymentStatus;
import zerobase.shoppingmall.payment.dto.entity.Payment;
import zerobase.shoppingmall.payment.repository.PaymentRepository;
import zerobase.shoppingmall.payment.service.PaymentService;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private static final String AUTHORIZATION = "KakaoAK 5d569ea19c6b8b53c9342d4d65a394e6";
    private static final String CONTENTTYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private final CartRepository cartRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Payment getPaymentInfo(PaymentInput paymentInput) { //인증용 URL을 리턴하도록 하기
        //결제 준비
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";

        //카트에서 토탈 값 가져오기,상품명, 총 금액
        List<Cart> cartList = cartRepository.findAllByUserId(paymentInput.getUserId())
            .orElseThrow(() -> new NotCartException());
        Long totalPrice = 0L;

        for (Cart cart : cartList) {
            totalPrice += cart.getPrice() * cart.getCountProduct();
        }

        String parameter = "cid=TC0ONETIME" // 가맹점 코드
            + "&partner_order_id=" + paymentInput.getUserId() + totalPrice.toString()// 가맹점 주문번호
            + "&partner_user_id=" + paymentInput.getUserId() // 가맹점 회원 id
            + "&item_name=" + cartList.get(0).getProductName() // 상품명
            + "&quantity=1" // 상품 수량
            + "&total_amount=" + totalPrice.toString() // 총 금액
            + "&vat_amount=1000"  //부가세
            + "&tax_free_amount=0" // 상품 비과세 금액
            + "&approval_url=http://localhost:8080/kakaopay/pgtoken" // 결제 성공 시
            + "&fail_url=http://localhost:8080/" // 결제 실패 시
            + "&cancel_url=http://localhost:8080/"; // 결제 취소 시

        Map<String, String> map = restTemplate.postForObject(url,
            new HttpEntity<>(parameter, getHeaders()), Map.class);

        Payment payment = Payment.builder()
            .price(totalPrice)
            .userId(paymentInput.getUserId())
            .productId(cartList.get(0).getProductId())
            .status(PaymentStatus.PAYMENT_WAIT)
            .createDate(LocalDateTime.now())
            .tid(map.get("tid"))
            .build();

        //카트 삭제
        cartRepository.deleteAllByUserId(paymentInput.getUserId());

        log.info(map.toString());

        return paymentRepository.save(payment);
    }

    @Override
    public Payment paymentSuccess(PaymentInput paymentInput) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";

        String parameter = "cid=TC0ONETIME"
            + "&tid=" + paymentInput.getTid()
            + "&partner_order_id=" + paymentInput.getUserId() + paymentInput.getPrice().toString()
            + "&partner_user_id=" + paymentInput.getUserId()
            + "&pg_token=" + paymentInput.getPg();

        Map<String, String> map = restTemplate.postForObject(url,
            new HttpEntity<>(parameter, getHeaders()), Map.class);

        log.info(map.toString());

        Payment payment = paymentRepository.findById(paymentInput.getPaymentId())
            .orElseThrow(() -> new NotPaymentHistoryException());

        payment.setStatus(PaymentStatus.PAID);
        payment.setPgToken(paymentInput.getPg());

        return paymentRepository.save(payment);
    }

    @Override
    public Payment paymentCancel(PaymentInput paymentInput) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/cancel";

        String parameter = "cid=TC0ONETIME"
            + "&tid=" + paymentInput.getTid()
            + "&cancel_amount=" + paymentInput.getPrice().toString()
            + "&cancel_tax_free_amount=0"
            + "&pg_token=" + paymentInput.getPg();

        Map<String, String> map = restTemplate.postForObject(url,
            new HttpEntity<>(parameter, getHeaders()), Map.class);

        log.info(map.toString());

        Payment payment = paymentRepository.findById(paymentInput.getPaymentId())
            .orElseThrow(() -> new NotPaymentHistoryException());

        payment.setStatus(PaymentStatus.CANCELED);

        return paymentRepository.save(payment);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AUTHORIZATION);
        headers.add("Content-type", CONTENTTYPE);

        return headers;
    }

}
