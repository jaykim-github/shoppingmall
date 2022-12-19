package zerobase.shoppingmall.admin.coupon.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.shoppingmall.admin.coupon.dto.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

   Optional<Coupon> findByCouponMasterIdAndUserId(Long couponMasterId,String userId);

   Optional<Long> findAllByCouponMasterId(Long couponMasterId);
}
