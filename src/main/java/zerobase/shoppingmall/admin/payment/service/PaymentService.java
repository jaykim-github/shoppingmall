package zerobase.shoppingmall.admin.payment.service;

import zerobase.shoppingmall.admin.payment.dto.PaymentInput;

public interface PaymentService {

    String getPaymentInfo(PaymentInput paymentInput);

    void PaymentSuccess(String tid, String pg, String userId, Long paymentId);
}
