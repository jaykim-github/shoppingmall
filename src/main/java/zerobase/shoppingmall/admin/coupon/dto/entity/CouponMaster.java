package zerobase.shoppingmall.admin.coupon.dto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
public class CouponMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponMasterId;

    private String couponName;
    private int couponCount;

    private int discountValue;
}
