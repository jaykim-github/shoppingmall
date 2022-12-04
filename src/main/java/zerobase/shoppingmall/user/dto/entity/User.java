package zerobase.shoppingmall.user.dto.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {

    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;

    private String zipcode;
    private String address;
    private String addressDetail;

    private LocalDateTime registered_date;

    private int emailAuth;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    //0:탈퇴, 1:가입
    private int userStatus;

    private int admin;
}
