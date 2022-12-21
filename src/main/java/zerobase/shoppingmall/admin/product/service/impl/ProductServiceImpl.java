package zerobase.shoppingmall.admin.product.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zerobase.shoppingmall.admin.product.dto.ProductInput;
import zerobase.shoppingmall.admin.product.dto.ProductStatus;
import zerobase.shoppingmall.admin.product.dto.entity.Product;
import zerobase.shoppingmall.admin.product.repository.ProductRepository;
import zerobase.shoppingmall.admin.product.service.ProductService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product register(ProductInput productInput) {

        Product product = Product.builder()
            .productName(productInput.getProductName())
            .productDescription(productInput.getProductDescription())
            .price(productInput.getPrice())
            .status(ProductStatus.ON_SALE)
            .build();

        return productRepository.save(product);
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        //status 1 인 것들만
        return productRepository.findAllByStatus(pageable, ProductStatus.ON_SALE);
    }

    @Override
    public Product deleteProduct(Long productId) {
        Product product = getProduct(productId);
        product.setStatus(ProductStatus.NOT_FOR_SALE);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, ProductInput productInput) {
        Product product = getProduct(productId);

        if (productInput.getPrice() != null) {
            product.setPrice(productInput.getPrice());
        }
        if (productInput.getProductName() != null) {
            product.setProductName(productInput.getProductName());
        }
        if (productInput.getProductDescription() != null) {
            product.setProductDescription(productInput.getProductDescription());
        }
        if (productInput.getStatus() != null) {
            product.setStatus(productInput.getStatus());
        }

        return productRepository.save(product);
    }

    @Override
    public Product getProductDetail(Long productId) {
        return getProduct(productId);
    }

    private Product getProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("등록된 상품이 없습니다.");
        }

        return Product.builder()
            .productId(productId)
            .productDescription(optionalProduct.get().getProductDescription())
            .price(optionalProduct.get().getPrice())
            .productName(optionalProduct.get().getProductName())
            .status(optionalProduct.get().getStatus())
            .build();
    }
}
