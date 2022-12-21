package zerobase.shoppingmall.admin.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentInput {

    private String userId;
    private Long cartId;

    private Long couponId;

    private PaymentStatus status;
    private Long price;

    private String tid;
    private String pg;
    private Long paymentId;
}
