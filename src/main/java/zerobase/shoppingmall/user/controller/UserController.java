package zerobase.shoppingmall.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.shoppingmall.response.BaseResponse;
import zerobase.shoppingmall.user.dto.UserInput;
import zerobase.shoppingmall.user.service.UserService;

@RestController
public class UserController {  //PR테스트
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/user")
    public BaseResponse createUser(@RequestBody UserInput userInput) {
        return userService.register(userInput);
    }

    @GetMapping("/user/email_auth")
    public BaseResponse emailAuth(@RequestParam String id) {
        String uuid = id;

        return userService.emailAuth(uuid);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody UserInput userInput) {
        String user_id = userInput.getUser_id();
        String password = userInput.getPassword();

        return userService.login(user_id, password);
    }

}
