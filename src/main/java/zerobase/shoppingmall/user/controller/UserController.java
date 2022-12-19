package zerobase.shoppingmall.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.shoppingmall.response.BaseResponse;
import zerobase.shoppingmall.user.dto.UserInput;
import zerobase.shoppingmall.user.dto.entity.User;
import zerobase.shoppingmall.user.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<Object> createUser(@RequestBody UserInput userInput) {

        User user = userService.register(userInput);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/email_auth")
    public ResponseEntity<Object> emailAuth(@RequestParam String id) {
        String uuid = id;
        User user = userService.emailAuth(uuid);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserInput userInput) {
        String user_id = userInput.getUser_id();
        String password = userInput.getPassword();

        Boolean response = userService.login(user_id, password);
        return ResponseEntity.ok(response);
    }

}
