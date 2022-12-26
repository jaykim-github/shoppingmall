package zerobase.shoppingmall.exception.Impl;

import org.springframework.http.HttpStatus;
import zerobase.shoppingmall.exception.CustomException;

public class NoCouponMasterException extends CustomException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "잘못된 쿠폰 입니다.";
    }
}
