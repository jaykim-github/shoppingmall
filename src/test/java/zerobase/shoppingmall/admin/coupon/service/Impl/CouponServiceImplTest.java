package zerobase.shoppingmall.admin.coupon.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zerobase.shoppingmall.admin.coupon.dto.CouponInput;
import zerobase.shoppingmall.admin.coupon.dto.entity.Coupon;
import zerobase.shoppingmall.admin.coupon.dto.entity.CouponMaster;
import zerobase.shoppingmall.admin.coupon.repository.CouponMasterRepository;
import zerobase.shoppingmall.admin.coupon.repository.CouponRepository;
import zerobase.shoppingmall.user.dto.entity.User;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CouponServiceImplTest {

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponMasterRepository couponMasterRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    @Test
    void successInssuedCoupon() {
        //given
        User user = User.builder().userId("test@mail.com").build();
        CouponMaster couponMaster = CouponMaster.builder().couponMasterId(1L).couponCount(10).build();

        CouponInput couponInput = CouponInput.builder()
            .couponMasterId(1L)
            .userId("test@mail.com")
            .status(1)
            .build();

        given(couponMasterRepository.findById(anyLong())).willReturn(Optional.of(couponMaster));

        given(couponRepository.countByCouponMasterIdAndUserId(couponMaster.getCouponMasterId(),user.getUserId()))
            .willReturn(0);


        ArgumentCaptor<Coupon> captor = ArgumentCaptor.forClass(Coupon.class);

        //when
        Coupon coupon = couponService.issuedCoupon(couponInput);

        //then
        verify(couponRepository, times(1)).save(captor.capture());
        assertEquals("test@mail.com", captor.getValue().getUserId());
        assertEquals(1L, captor.getValue().getCouponMasterId());
    }



}