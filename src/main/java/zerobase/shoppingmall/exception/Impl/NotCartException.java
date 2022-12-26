package zerobase.shoppingmall.exception.Impl;

import org.springframework.http.HttpStatus;
import zerobase.shoppingmall.exception.CustomException;

public class NotCartException extends CustomException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "장바구니가 없습니다.";
    }
}
