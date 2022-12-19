package zerobase.shoppingmall.admin.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zerobase.shoppingmall.admin.product.dto.ProductInput;
import zerobase.shoppingmall.admin.product.dto.entity.Product;

public interface ProductService {

    Product register(ProductInput productInput);

    Page<Product> getAllProduct(Pageable pageable);

    Product deleteProduct(Long productId);

    Product updateProduct(Long productId, ProductInput productInput);

    Product getProductDetail(Long productId);
}
