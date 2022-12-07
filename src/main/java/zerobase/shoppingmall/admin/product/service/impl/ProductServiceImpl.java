package zerobase.shoppingmall.admin.product.service.impl;

import jodd.net.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.shoppingmall.admin.product.dto.ProductInput;
import zerobase.shoppingmall.admin.product.dto.entity.Product;
import zerobase.shoppingmall.admin.product.repository.ProductRepository;
import zerobase.shoppingmall.admin.product.service.ProductService;
import zerobase.shoppingmall.response.BaseResponse;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public BaseResponse register(ProductInput productInput) {

        Product product = Product.builder()
            .productName(productInput.getProductName())
            .productDescription(productInput.getProductDescription())
            .price(productInput.getPrice())
            .status(1)
            .build();

        productRepository.save(product);

        return BaseResponse.builder()
            .code(HttpStatus.ok().status())
            .httpStatus(HttpStatus.ok())
            .message("상품 등록에 성공하였습니다.")
            .build();
    }
}
