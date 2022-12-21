package zerobase.shoppingmall.admin.payment.dto.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import zerobase.shoppingmall.admin.payment.dto.PaymentStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Audited
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private String tid; //결제 고유 번호

    @NotAudited
    private String pgToken; //결제 승인 인증 토큰

    @NotAudited
    private Long price;

    @NotAudited
    private Long productId;

    @NotAudited
    private Long couponId;

    private String userId;

    @NotAudited
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime createDate;
    private LocalDateTime canceledDate;
}
