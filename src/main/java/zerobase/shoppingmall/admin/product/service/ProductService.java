package zerobase.shoppingmall.admin.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zerobase.shoppingmall.admin.product.dto.ProductInput;
import zerobase.shoppingmall.admin.product.dto.entity.Product;
import zerobase.shoppingmall.response.BaseResponse;

public interface ProductService {

    public BaseResponse register(ProductInput productInput);

    Page<Product> getAllProduct(Pageable pageable);

    BaseResponse deleteProduct(Long productId);

    BaseResponse updateProduct(Long productId, ProductInput productInput);

    Product getProductDettail(Long productId);
}
