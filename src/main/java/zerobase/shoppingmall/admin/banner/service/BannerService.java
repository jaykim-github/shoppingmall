package zerobase.shoppingmall.admin.banner.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import zerobase.shoppingmall.admin.banner.dto.BannerInput;
import zerobase.shoppingmall.admin.banner.dto.entity.Banner;

public interface BannerService {

    Banner register(BannerInput bannerInput, MultipartFile file);

    List<Banner> getAllBanner();

    Banner getBannerDetail(Long id);

    Banner updateBanner(MultipartFile file, Long bannerId, BannerInput bannerInput);

    Banner deleteBanner(Long bannerId);
}
