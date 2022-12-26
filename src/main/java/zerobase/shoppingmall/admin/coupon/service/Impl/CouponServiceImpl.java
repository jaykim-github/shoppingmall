package zerobase.shoppingmall.admin.coupon.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zerobase.shoppingmall.admin.coupon.dto.CouponInput;
import zerobase.shoppingmall.admin.coupon.dto.entity.Coupon;
import zerobase.shoppingmall.admin.coupon.dto.entity.CouponMaster;
import zerobase.shoppingmall.admin.coupon.repository.CouponMasterRepository;
import zerobase.shoppingmall.admin.coupon.repository.CouponRepository;
import zerobase.shoppingmall.admin.coupon.service.CouponService;
import zerobase.shoppingmall.exception.Impl.AlreadtyIssuedCouponException;
import zerobase.shoppingmall.exception.Impl.NoCouponMasterException;
import zerobase.shoppingmall.exception.Impl.NoLeftCountCouponException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMasterRepository couponMasterRepository;

    @Override
    @Cacheable(key = "#couponInput.couponMasterId", value = "coupon")
    public Coupon issuedCoupon(CouponInput couponInput) {
        //아이디 쿠폰 마스터 확인하기
        CouponMaster couponMaster = couponMasterRepository.findById(couponInput.getCouponMasterId())
            .orElseThrow(() -> new NoCouponMasterException());

        int countCoupon = couponRepository.countByCouponMasterIdAndUserId(
            couponInput.getCouponMasterId(), couponInput.getUserId());

        if (countCoupon > 0) {
            throw new AlreadtyIssuedCouponException();
        }

        Long couponCount = couponRepository.countAllByCouponMasterId(
            couponInput.getCouponMasterId());

        if (couponMaster.getCouponCount() <= couponCount) {
            throw new NoLeftCountCouponException();
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
