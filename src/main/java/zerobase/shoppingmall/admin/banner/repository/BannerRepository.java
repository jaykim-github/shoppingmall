package zerobase.shoppingmall.admin.banner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.shoppingmall.admin.banner.dto.entity.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {

}
