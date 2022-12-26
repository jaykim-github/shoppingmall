package zerobase.shoppingmall.payment.service;

import zerobase.shoppingmall.payment.dto.PaymentInput;
import zerobase.shoppingmall.payment.dto.entity.Payment;

public interface PaymentService {

    Payment getPaymentInfo(PaymentInput paymentInput);

    Payment paymentSuccess(PaymentInput paymentInput);

    Payment paymentCancel(PaymentInput paymentInput);
}
