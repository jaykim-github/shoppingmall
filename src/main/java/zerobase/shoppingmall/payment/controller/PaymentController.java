package zerobase.shoppingmall.payment.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.shoppingmall.payment.dto.PaymentInput;
import zerobase.shoppingmall.payment.dto.entity.Payment;
import zerobase.shoppingmall.payment.service.PaymentService;

@Slf4j
@AllArgsConstructor
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/kakaopay")
    public ResponseEntity<Object> kakaopay(@RequestBody PaymentInput paymentInput) {
        //연동 완료 -> response 값 수정 필요
        Payment payment = paymentService.getPaymentInfo(paymentInput);
        return ResponseEntity.ok(payment);
    }


    @PostMapping("/kakaopay/approve")
    public ResponseEntity<Object> kakaopayApprove(@RequestBody PaymentInput paymentInput) {
        Payment payment = paymentService.paymentSuccess(paymentInput);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/kakaopay/pgtoken")
    public String pgtoken(@RequestParam(value = "pg_token") String request) {
        log.info(request);
        return request;
    }

    @PostMapping("/kakaopay/cancel")
    public ResponseEntity<Object> kakaopayCancel(@RequestBody PaymentInput paymentInput) {
        Payment payment = paymentService.paymentCancel(paymentInput);
        return ResponseEntity.ok(payment);
    }
}
