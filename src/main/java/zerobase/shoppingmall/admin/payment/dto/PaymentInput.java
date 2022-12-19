package zerobase.shoppingmall.admin.payment.dto;

import java.time.LocalDateTime;
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

    private int status;
    private LocalDateTime createDate;
}
