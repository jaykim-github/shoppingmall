package zerobase.shoppingmall.admin.banner.dto.entity;

import java.time.LocalDate;
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
import zerobase.shoppingmall.admin.banner.dto.BannerStatus;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagePath;
    private String bannerName;
    private String alterText;
    private String toUrl;

    private int sortValue;

    @Enumerated(value = EnumType.STRING)
    private BannerStatus status;

    private LocalDate startDate;
    private LocalDate endDate;

}
