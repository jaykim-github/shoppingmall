package zerobase.shoppingmall.admin.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.shoppingmall.admin.product.dto.ProductStatus;
import zerobase.shoppingmall.admin.product.dto.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page findAllByStatus(Pageable pageable, ProductStatus status);
}
