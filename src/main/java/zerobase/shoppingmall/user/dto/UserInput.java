package zerobase.shoppingmall.user.dto;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

    private String userId;
    private String userName;
    private String phone;
    private String password;

    private String zipcode;
    private String address;
    private String addressDetail;
    private List<String> roles;

}
