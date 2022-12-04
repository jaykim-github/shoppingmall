package zerobase.shoppingmall.user.service;

import zerobase.shoppingmall.response.BaseResponse;
import zerobase.shoppingmall.user.dto.UserInput;

public interface UserService {
    BaseResponse register(UserInput userInput);

    BaseResponse emailAuth(String uuid);

    BaseResponse login(String user_id, String password);
}
