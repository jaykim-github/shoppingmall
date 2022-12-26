package zerobase.shoppingmall.admin.coupon.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.shoppingmall.admin.coupon.dto.CouponMasterInput;
import zerobase.shoppingmall.admin.coupon.dto.entity.CouponMaster;
import zerobase.shoppingmall.admin.coupon.service.CouponMasterService;

@AllArgsConstructor
@RestController
public class CouponMasterController {
    //쿠폰 생성, 삭제

    private final CouponMasterService couponMasterService;

    @PostMapping("/couponMaster/create")
    public ResponseEntity<Object> createCoupon(@RequestBody CouponMasterInput couponMasterInput) {
        if(couponMasterInput == null){
            throw new RuntimeException("입력된 정보가 없습니다.");
        }

        CouponMaster couponMaster = couponMasterService.createCoupon(couponMasterInput);
        return ResponseEntity.ok(couponMaster);
    }

    @GetMapping("/couponMaster")
    public ResponseEntity<Object> couponMasterList(Pageable pageable) {
        Page<CouponMaster> couponMasters = couponMasterService.getAllCoupon(pageable);
        return ResponseEntity.ok(couponMasters);
    }

    @PutMapping("/couponMaster/update")
    public ResponseEntity<Object> updateCouponMaster(@RequestParam Long couponMasterId,
        @RequestBody CouponMasterInput couponMasterInput) {
        CouponMaster couponMaster = couponMasterService.updateCouponMaster(couponMasterId,couponMasterInput);

        return ResponseEntity.ok(couponMaster);
    }


    @DeleteMapping("/couponMaster/delete")
    public ResponseEntity<Object> deleteCouponMaster(@RequestParam Long couponMasterId){
        String couponMasterName = couponMasterService.deleteCouponMaster(couponMasterId);

        return ResponseEntity.ok(couponMasterName);
    }
}
