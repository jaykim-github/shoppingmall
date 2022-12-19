package zerobase.shoppingmall.admin.coupon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/coupon/inssued")
    public ResponseEntity<Object> inssuedCoupon(@RequestBody CouponInput couponInput) {
        if(couponInput == null){
            throw new RuntimeException("입력된 정보가 없습니다.");
        }

        Coupon coupon = couponService.inssuedCoupon(couponInput);
        return ResponseEntity.ok(coupon);
    }


}
