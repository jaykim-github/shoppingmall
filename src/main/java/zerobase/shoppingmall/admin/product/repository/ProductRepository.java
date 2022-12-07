package zerobase.shoppingmall.admin.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.shoppingmall.admin.product.dto.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
