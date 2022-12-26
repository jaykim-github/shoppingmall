package zerobase.shoppingmall.exception.Impl;

import org.springframework.http.HttpStatus;
import zerobase.shoppingmall.exception.CustomException;

public class WrongAuthEmailException extends CustomException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "잘못된 인증 메일입니다.";
    }
}
