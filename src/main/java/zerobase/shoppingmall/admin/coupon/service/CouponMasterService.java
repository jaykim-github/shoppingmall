package zerobase.shoppingmall.admin.coupon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zerobase.shoppingmall.admin.coupon.dto.CouponMasterInput;
import zerobase.shoppingmall.admin.coupon.dto.entity.CouponMaster;

public interface CouponMasterService {

    CouponMaster createCoupon(CouponMasterInput couponMasterInput);

    String deleteCouponMaster(Long couponMasterId);

    Page<CouponMaster> getAllCoupon(Pageable pageable);

    CouponMaster updateCouponMaster(Long couponMasterId, CouponMasterInput couponMasterInput);
}
