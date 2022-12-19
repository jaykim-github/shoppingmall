package zerobase.shoppingmall.admin.payment.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.shoppingmall.admin.payment.dto.PaymentInput;
import zerobase.shoppingmall.admin.payment.dto.entity.Payment;
import zerobase.shoppingmall.admin.payment.service.PaymentService;

@AllArgsConstructor
@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/kakaopay")
    public String kakaopay(@RequestBody PaymentInput paymentInput) {
        //연동 완료 -> response 값 수정 필요
        return paymentService.getPaymentInfo(paymentInput);
    }


    @GetMapping("kakaopay/approve")
    public void kakaopayApprove(String tid,String pg,String userId,Long paymentId){
        paymentService.PaymentSuccess(tid,pg,userId,paymentId);
    }

    @GetMapping("/kakaopay/pgtoken")
    public String pgtoken(@RequestParam(value="pg_token") String request){
        System.out.println(request);
        return request;
    }
}
