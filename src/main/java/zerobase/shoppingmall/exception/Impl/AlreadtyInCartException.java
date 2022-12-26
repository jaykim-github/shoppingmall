package zerobase.shoppingmall.exception.Impl;

import org.springframework.http.HttpStatus;
import zerobase.shoppingmall.exception.CustomException;

public class AlreadtyInCartException extends CustomException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "이미 카트에 담긴 상품입니다.";
    }
}
