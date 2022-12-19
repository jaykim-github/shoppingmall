package zerobase.shoppingmall.admin.coupon.service.Impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerobase.shoppingmall.admin.coupon.dto.entity.CouponMaster;
import zerobase.shoppingmall.admin.coupon.repository.CouponRepository;
import zerobase.shoppingmall.admin.coupon.dto.CouponInput;
import zerobase.shoppingmall.admin.coupon.dto.entity.Coupon;
import zerobase.shoppingmall.admin.coupon.repository.CouponMasterRepository;
import zerobase.shoppingmall.admin.coupon.service.CouponService;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMasterRepository couponMasterRepository;

    @Override
    public Coupon inssuedCoupon(CouponInput couponInput) {
        //아이디 쿠폰 마스터 확인하기
        CouponMaster couponMaster = couponMasterRepository.findById(couponInput.getCouponMasterId())
            .orElseThrow(() -> new RuntimeException("존재하지 않는 쿠폰입니다."));

        Optional<Coupon> optionalCoupon = couponRepository.findByCouponMasterIdAndUserId(couponInput.getCouponMasterId(),couponInput.getUserId());

        if(optionalCoupon.isPresent()){
            throw new RuntimeException("이미 발급 받은 쿠폰입니다.");
        }

        Long couponCount = couponRepository.findAllByCouponMasterId(couponInput.getCouponMasterId()).orElse(
            couponMaster.getCouponMasterId()-couponMaster.getCouponMasterId());

        if(couponMaster.getCouponCount() <= couponCount && couponCount != null){
            throw new RuntimeException("품절된 쿠폰입니다.");
        }

        Coupon coupon = Coupon.builder()
            .couponMasterId(couponInput.getCouponMasterId())
            .couponName(couponMaster.getCouponName())
            .discountValue(couponMaster.getDiscountValue())
            .userId(couponInput.getUserId())
            .build();

        return couponRepository.save(coupon);
    }
}
