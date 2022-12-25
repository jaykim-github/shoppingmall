package zerobase.shoppingmall.admin.banner.dto;

import java.time.LocalDate;
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
public class BannerInput {
    private String imagePath;
    private String bannerName;
    private String alterText;
    private String toUrl;

    private int sortValue;

    private BannerStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
}
