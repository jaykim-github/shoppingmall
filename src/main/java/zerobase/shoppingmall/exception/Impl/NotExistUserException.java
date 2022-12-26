package zerobase.shoppingmall.exception.Impl;

import org.springframework.http.HttpStatus;
import zerobase.shoppingmall.exception.CustomException;

public class NotExistUserException extends CustomException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "등록되지 않은 사용자입니다.";
    }
}
