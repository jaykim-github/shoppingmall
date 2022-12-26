package zerobase.shoppingmall.admin.coupon.dto;

import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponInput {

    @NotNull
    private Long couponMasterId;

    @NotNull
    @Id
    private String userId;

    private int status;
    private LocalDateTime usingDate;
}
