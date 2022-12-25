package zerobase.shoppingmall.admin.coupon.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.shoppingmall.admin.coupon.dto.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    int countByCouponMasterIdAndUserId(Long couponMasterId, String userId);

    Long countAllByCouponMasterId(Long couponMasterId);

    void deleteByUserId(String key);
}
