package zerobase.shoppingmall.admin.coupon.dto;

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
public class CouponMasterInput {
    private String couponName;
    private int couponCount;

    private int discountValue;
}
