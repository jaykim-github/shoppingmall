package zerobase.shoppingmall.admin.product.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            .message("상품 등록에 성공하였습니다.")
            .build();
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public BaseResponse deleteProduct(Long productId) {
        Optional<Product> optionalProduct =  getProduct(productId);

        Product product = optionalProduct.get();
        product.setStatus(0);
        productRepository.save(product);

        return BaseResponse.builder()
            .message("상품이 정상적으로 삭제 되었습니다.")
            .build();
    }

    @Override
    public BaseResponse updateProduct(Long productId, ProductInput productInput) {
        Optional<Product> optionalProduct =  getProduct(productId);

        Product product = optionalProduct.get();
        product.setProductName(productInput.getProductName());
        product.setProductDescription(productInput.getProductDescription());
        product.setPrice(productInput.getPrice());
        productRepository.save(product);

        return BaseResponse.builder()
            .message("상품이 정상적으로 수정 되었습니다.")
            .build();
    }

    @Override
    public Product getProductDettail(Long productId) {
        Optional<Product> optionalProduct = getProduct(productId);
        Product product = optionalProduct.get();

        return product;
    }

    private Optional<Product> getProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(!optionalProduct.isPresent()){
            throw new RuntimeException("등록된 상품이 없습니다.");
        }

        return optionalProduct;
    }
}
