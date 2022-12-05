package zerobase.shoppingmall.user.service.Impl;

import java.time.LocalDateTime;

import java.util.Optional;
import java.util.UUID;
import jodd.net.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.shoppingmall.component.MailComponents;
import zerobase.shoppingmall.response.BaseResponse;
import zerobase.shoppingmall.user.dto.UserInput;
import zerobase.shoppingmall.user.dto.entity.User;
import zerobase.shoppingmall.user.repository.UserRepository;
import zerobase.shoppingmall.user.service.UserService;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailComponents mailComponents;

    //BaseResponse baseResponse;
    @Override
    public BaseResponse register(UserInput userInput) {

        Optional<User> optionalUser = getUser(userInput.getUser_id());

        if (optionalUser.isPresent()) {
            return BaseResponse.builder()
                .code(409)
                .httpStatus(HttpStatus.error409())
                .message("이미 등록된 아이디 입니다.")
                .build();
        }

        //시큐리티 적용 후 사용
        //String encPassword = BCrypt.hashpw(userInput.getPassword(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        User user = User.builder()
            .userId(userInput.getUser_id())
            .userName(userInput.getUserName())
            .phone(userInput.getPhone())
            .password(userInput.getPassword())
            .zipcode(userInput.getZipcode())
            .address(userInput.getAddress())
            .addressDetail(userInput.getAddressDetail())
            .registered_date(LocalDateTime.now())
            .userStatus(1)
            .emailAuthKey(uuid)
            .emailAuth(0)
            .build();

        userRepository.save(user);

        String email = userInput.getUser_id();
        String subject = "가입 인증 메일입니다.";
        String text = "<p>아래의 링크를 클릭하셔서" +
            "가입을 완료하세요.</p> <div><a href='http://localhost:8080/user/email_auth?id="
            + uuid + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return BaseResponse.builder()
            .code(HttpStatus.ok().status())
            .httpStatus(HttpStatus.ok())
            .message("회원 등록에 성공했습니다. 이메일 인증해주세요.")
            .build();
    }

    @Override
    public BaseResponse emailAuth(String uuid) {
        BaseResponse baseResponse;
        Optional<User> optionalUser = userRepository.findByEmailAuthKey(uuid);
        if (!optionalUser.isPresent()) {
            return baseResponse = BaseResponse.builder()
                .code(409)
                .httpStatus(HttpStatus.error409())
                .message("잘못된 인증 메일입니다. 시스템에 문의하여 주세요.")
                .build();
        }

        User user = optionalUser.get();
        if (user.getEmailAuth() == 1) {
            return baseResponse = BaseResponse.builder()
                .code(409)
                .httpStatus(HttpStatus.error409())
                .message("이미 인증된 메일입니다.")
                .build();
        }

        user.setEmailAuth(1);
        user.setEmailAuthDt(LocalDateTime.now());

        userRepository.save(user);

        return BaseResponse.builder()
            .code(HttpStatus.ok().status())
            .httpStatus(HttpStatus.ok())
            .message("이메일 인증에 성공하였습니다.")
            .build();
    }

    @Override
    public BaseResponse login(String user_id, String password) {
        Optional<User> optionalUser = getUser(user_id);
        if (!optionalUser.isPresent()) {
            return BaseResponse.builder()
                .code(409)
                .httpStatus(HttpStatus.error409())
                .message("등록되지 않은 아이디 입니다.")
                .build();
        }
        User user = optionalUser.get();

        if (user.getPassword() != password) {
            return BaseResponse.builder()
                .code(409)
                .httpStatus(HttpStatus.error409())
                .message("비밀번호가 일치하지 않습니다.")
                .build();
        }

        return BaseResponse.builder()
            .code(HttpStatus.ok().status())
            .httpStatus(HttpStatus.ok())
            .message("로그인에 성공하였습니다.")
            .build();
    }

    private Optional<User> getUser(String user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        return optionalUser;
    }


}
