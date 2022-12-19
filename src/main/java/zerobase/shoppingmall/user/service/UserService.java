package zerobase.shoppingmall.user.service;

import zerobase.shoppingmall.response.BaseResponse;
import zerobase.shoppingmall.user.dto.UserInput;
import zerobase.shoppingmall.user.dto.entity.User;

public interface UserService {
    User register(UserInput userInput);

    User emailAuth(String uuid);

    Boolean login(String user_id, String password);
}
