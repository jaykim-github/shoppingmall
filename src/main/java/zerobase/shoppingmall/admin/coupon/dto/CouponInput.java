package zerobase.shoppingmall.admin.coupon.dto;

import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponInput {

    @NotNull
    private Long couponMasterId;

    @NotNull
    private String userId;

    private int status;
    private LocalDateTime usingDate;
}
