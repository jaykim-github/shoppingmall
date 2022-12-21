package zerobase.shoppingmall.admin.payment.service;

import zerobase.shoppingmall.admin.payment.dto.PaymentInput;
import zerobase.shoppingmall.admin.payment.dto.entity.Payment;

public interface PaymentService {

    Payment getPaymentInfo(PaymentInput paymentInput);

    Payment paymentSuccess(PaymentInput paymentInput);

    Payment paymentCancel(PaymentInput paymentInput);
}
