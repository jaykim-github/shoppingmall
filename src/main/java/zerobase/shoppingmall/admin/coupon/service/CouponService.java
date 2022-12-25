package zerobase.shoppingmall.admin.coupon.service;

import zerobase.shoppingmall.admin.coupon.dto.CouponInput;
import zerobase.shoppingmall.admin.coupon.dto.entity.Coupon;

public interface CouponService {

    Coupon issuedCoupon(CouponInput couponInput);
}
