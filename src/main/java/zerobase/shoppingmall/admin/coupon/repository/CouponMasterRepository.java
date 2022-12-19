package zerobase.shoppingmall.admin.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import zerobase.shoppingmall.admin.coupon.dto.entity.CouponMaster;

public interface CouponMasterRepository extends JpaRepository<CouponMaster, Long> {
    @Transactional
    void deleteById(Long couponMasterId);
}
