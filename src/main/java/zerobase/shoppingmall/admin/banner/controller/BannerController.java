package zerobase.shoppingmall.admin.banner.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zerobase.shoppingmall.admin.banner.dto.BannerInput;
import zerobase.shoppingmall.admin.banner.dto.entity.Banner;
import zerobase.shoppingmall.admin.banner.service.BannerService;
import zerobase.shoppingmall.admin.product.dto.entity.Product;

@Slf4j
@AllArgsConstructor
@RestController
public class BannerController {

    private final BannerService bannerService;

    @PostMapping("/banner/register")
    public ResponseEntity<Object> createBanner(
        @RequestPart(value = "file", required = false)MultipartFile file,
        @RequestPart(value = "bannerInput") BannerInput bannerInput) {
        if (bannerInput == null) {
            throw new RuntimeException();
        }

        Banner banner = bannerService.register(bannerInput, file);

        return ResponseEntity.ok(banner);
    }

    @GetMapping("/banner")
    public ResponseEntity<Object>  bannerList(){
        List<Banner> banner = bannerService.getAllBanner();

        return ResponseEntity.ok(banner);
    }

    @GetMapping("/banner/detail")
    public ResponseEntity<Object> bannerDetail(@RequestParam Long bannerId){
        Banner banner = bannerService.getBannerDetail(bannerId);

        return ResponseEntity.ok(banner);
    }

    @PutMapping("/banner/update")
    public ResponseEntity<Object> updateProduct(@RequestParam Long bannerId,
        @RequestPart(value = "file", required = false)MultipartFile file,
        @RequestPart(value = "bannerInput") BannerInput bannerInput) {
        Banner banner = bannerService.updateBanner(file,bannerId, bannerInput);

        return ResponseEntity.ok(banner);
    }

    @DeleteMapping("/banner/delete")
    public ResponseEntity<Object> deleteBanner(@RequestParam Long bannerId) {
        Banner banner = bannerService.deleteBanner(bannerId);

        return ResponseEntity.ok(banner);
    }
}
