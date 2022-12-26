package zerobase.shoppingmall.admin.coupon.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zerobase.shoppingmall.admin.coupon.dto.CouponMasterInput;
import zerobase.shoppingmall.admin.coupon.dto.entity.CouponMaster;
import zerobase.shoppingmall.admin.coupon.repository.CouponMasterRepository;
import zerobase.shoppingmall.admin.coupon.service.CouponMasterService;
import zerobase.shoppingmall.exception.Impl.NoCouponMasterException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponMasterServiceImpl implements CouponMasterService {

    private final CouponMasterRepository couponMasterRepository;

    @Override
    public CouponMaster createCoupon(CouponMasterInput couponMasterInput) {
        CouponMaster couponMaster = CouponMaster.builder()
            .couponName(couponMasterInput.getCouponName())
            .couponCount(couponMasterInput.getCouponCount())
            .discountValue(couponMasterInput.getDiscountValue())
            .build();

        return couponMasterRepository.save(couponMaster);
    }

    @Override
    public Page<CouponMaster> getAllCoupon(Pageable pageable) {
        return couponMasterRepository.findAll(pageable);
    }

    @Override
    public CouponMaster updateCouponMaster(Long couponMasterId,
        CouponMasterInput couponMasterInput) {
        CouponMaster couponMaster = couponMasterRepository.findById(couponMasterId)
            .orElseThrow(() -> new NoCouponMasterException());

        if(couponMasterInput.getCouponCount() != 0){
            couponMaster.setCouponCount(couponMasterInput.getCouponCount());
        }
        if(couponMasterInput.getCouponName() != null){
            couponMaster.setCouponName(couponMasterInput.getCouponName());
        }
        if(couponMasterInput.getDiscountValue() != 0){
            couponMaster.setDiscountValue(couponMasterInput.getDiscountValue());
        }

        return couponMasterRepository.save(couponMaster);
    }

    @Override
    public String deleteCouponMaster(Long couponMasterId) {
        CouponMaster couponMaster = couponMasterRepository.findById(couponMasterId)
            .orElseThrow(() -> new NoCouponMasterException());

        couponMasterRepository.deleteById(couponMasterId);

        return couponMaster.getCouponName();
    }




}
