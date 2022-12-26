package zerobase.shoppingmall.admin.banner.service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import zerobase.shoppingmall.admin.banner.dto.BannerInput;
import zerobase.shoppingmall.admin.banner.dto.BannerStatus;
import zerobase.shoppingmall.admin.banner.dto.entity.Banner;
import zerobase.shoppingmall.admin.banner.repository.BannerRepository;
import zerobase.shoppingmall.admin.banner.service.BannerService;
import zerobase.shoppingmall.exception.Impl.NoBannerException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    @Override
    public Banner register(BannerInput bannerInput, MultipartFile file) {
        String urlFilename = "";
        String saveFilename = "";
        if (file != null) {
            String originalFilename = file.getOriginalFilename();

            String baseLocalPath = "C:\\dev\\shoppingmall\\files";
            String baseUrlPath = "\\files";
            String[] arrFilename = getNewSaveFile(baseLocalPath, baseUrlPath, originalFilename);

            saveFilename = arrFilename[0];
            urlFilename = arrFilename[1];

            try {
                File newFile = new File(saveFilename);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }

        Banner banner = Banner.builder()
            .bannerName(bannerInput.getBannerName())
            .alterText(bannerInput.getAlterText())
            .imagePath(urlFilename)
            .toUrl(bannerInput.getToUrl())
            .sortValue(bannerInput.getSortValue())
            .status(BannerStatus.USE)
            .startDate(bannerInput.getStartDate())
            .endDate(bannerInput.getEndDate())
            .build();

        return bannerRepository.save(banner);
    }

    @Override
    public List<Banner> getAllBanner() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner getBannerDetail(Long id) {
        Banner banner = getBanner(id);

        return Banner.builder()
            .id(id)
            .bannerName(banner.getBannerName())
            .imagePath(banner.getImagePath())
            .alterText(banner.getAlterText())
            .toUrl(banner.getToUrl())
            .sortValue(banner.getSortValue())
            .startDate(banner.getStartDate())
            .sortValue(banner.getSortValue())
            .endDate(banner.getEndDate())
            .status(banner.getStatus())
            .build();
    }

    @Override
    public Banner updateBanner(MultipartFile file, Long bannerId, BannerInput bannerInput) {
        Banner banner = getBanner(bannerId);

        String urlFilename = "";
        String saveFilename = "";
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            String baseLocalPath = "C:\\dev\\shoppingmall\\files";
            String baseUrlPath = "\\files";
            String[] arrFilename = getNewSaveFile(baseLocalPath, baseUrlPath, originalFilename);

            saveFilename = arrFilename[0];
            urlFilename = arrFilename[1];

            try {
                File newFile = new File(saveFilename);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));

                banner.setImagePath(urlFilename);
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }

        if (bannerInput.getBannerName() != null) {
            banner.setBannerName(bannerInput.getBannerName());
        }
        if (bannerInput.getToUrl() != null) {
            banner.setToUrl(bannerInput.getToUrl());
        }
        if (bannerInput.getSortValue() != 0) {
            banner.setSortValue(bannerInput.getSortValue());
        }
        if (bannerInput.getAlterText() != null) {
            banner.setAlterText(bannerInput.getAlterText());
        }

        if (bannerInput.getStatus() != banner.getStatus()) {
            banner.setStatus(bannerInput.getStatus());
        }
        if (bannerInput.getStartDate() != null) {
            banner.setStartDate(bannerInput.getStartDate());
        }
        if (bannerInput.getEndDate() != null) {
            banner.setEndDate(bannerInput.getEndDate());
        }

        return bannerRepository.save(banner);
    }

    @Override
    public Banner deleteBanner(Long bannerId) {
        Banner banner = getBanner(bannerId);

        banner.setStatus(BannerStatus.NOT_USE);

        return bannerRepository.save(banner);
    }

    public String[] getNewSaveFile(String baseLocalPath, String baseUrlPath,
        String originalFilename) {
        LocalDate now = LocalDate.now();
        String[] dirs = {
            String.format("%s\\%d\\", baseLocalPath, now.getYear()),
            String.format("%s\\%d\\%02d\\", baseLocalPath, now.getYear(), now.getMonthValue()),
            String.format("%s\\%d\\%02d\\", baseLocalPath, now.getYear(), now.getMonthValue(),
                now.getDayOfMonth())
        };

        String urlDir = String.format("%s\\%d\\%02d\\", baseUrlPath, now.getYear(),
            now.getMonthValue(), now.getDayOfMonth());

        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }
        String fileExtension = "";
        if (originalFilename != null) {
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFilename.substring(dotPos + 1);
            }
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String newUrlFilename = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFilename += "." + fileExtension;
            newUrlFilename += "." + fileExtension;
        }

        return new String[]{newFilename, newUrlFilename};
    }

    private Banner getBanner(Long bannerId) {
        return bannerRepository.findById(bannerId)
            .orElseThrow(() -> new NoBannerException());
    }

}
