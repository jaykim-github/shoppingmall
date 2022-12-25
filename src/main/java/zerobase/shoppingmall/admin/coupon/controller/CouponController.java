package zerobase.shoppingmall.admin.coupon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.shoppingmall.admin.coupon.dto.CouponInput;
import zerobase.shoppingmall.admin.coupon.dto.entity.Coupon;
import zerobase.shoppingmall.admin.coupon.service.CouponService;

@AllArgsConstructor
@RestController
public class CouponController {
    //쿠폰 생성, 삭제

    private final CouponService couponService;

    @PostMapping("/coupon/issued") // 기본 쿠폰 발급 -> 캐시 적용 필요
    public ResponseEntity<Object> issuedCoupon(@Validated @RequestBody CouponInput couponInput) {
        Coupon coupon = couponService.issuedCoupon(couponInput);
        return ResponseEntity.ok(coupon);
    }


}
