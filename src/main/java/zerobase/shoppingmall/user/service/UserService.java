package zerobase.shoppingmall.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.shoppingmall.component.MailComponents;
import zerobase.shoppingmall.exception.Impl.AlreadtyAuthedEmailException;
import zerobase.shoppingmall.exception.Impl.AlreadtyExistUserException;
import zerobase.shoppingmall.exception.Impl.NoAuthedEmailException;
import zerobase.shoppingmall.exception.Impl.NotExistUserException;
import zerobase.shoppingmall.exception.Impl.NotMatchPasswordException;
import zerobase.shoppingmall.exception.Impl.WrongAuthEmailException;
import zerobase.shoppingmall.response.BaseResponse;
import zerobase.shoppingmall.user.dto.UserInput;
import zerobase.shoppingmall.user.dto.entity.User;
import zerobase.shoppingmall.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MailComponents mailComponents;

    private final PasswordEncoder passwordEncoder;

    public User register(UserInput userInput) {

        Optional<User> optionalUser = getUser(userInput.getUserId());

        if (optionalUser.isPresent()) {
            throw new AlreadtyExistUserException();
        }

        String uuid = UUID.randomUUID().toString();

        User user = User.builder()
            .userId(userInput.getUserId())
            .userName(userInput.getUserName())
            .phone(userInput.getPhone())
            .password(passwordEncoder.encode(userInput.getPassword()))
            .zipcode(userInput.getZipcode())
            .address(userInput.getAddress())
            .addressDetail(userInput.getAddressDetail())
            .registered_date(LocalDateTime.now())
            .userStatus(1)
            .emailAuthKey(uuid)
            .emailAuth(0)
            .roles(userInput.getRoles())
            .build();

        String email = userInput.getUserId();
        String subject = "가입 인증 메일입니다.";
        String text = "<p>아래의 링크를 클릭하셔서" +
            "가입을 완료하세요.</p> <div><a href='http://localhost:8080/user/email_auth?id="
            + uuid + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return userRepository.save(user);
    }


    public User emailAuth(String uuid) {
        BaseResponse baseResponse;
        Optional<User> optionalUser = userRepository.findByEmailAuthKey(uuid);
        if (!optionalUser.isPresent()) {
            throw new WrongAuthEmailException();
        }

        User user = optionalUser.get();
        if (user.getEmailAuth() == 1) {
            throw new AlreadtyAuthedEmailException();
        }

        user.setEmailAuth(1);
        user.setEmailAuthDt(LocalDateTime.now());

        return userRepository.save(user);
    }


    public User login(String user_id, String password) {
        Optional<User> optionalUser = getUser(user_id);
        if (!optionalUser.isPresent()) {
            throw new NotExistUserException();
        }
        User user = optionalUser.get();

        if (user.getEmailAuth() != 1) {
            throw new NoAuthedEmailException();
        }

        if (passwordEncoder.matches(user.getPassword(), password)) {
            throw new NotMatchPasswordException();
        }

        return user;
    }

    private Optional<User> getUser(String user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        return optionalUser;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        return userRepository.findByUserId(username).orElseThrow(() -> new NotExistUserException());
    }
}
