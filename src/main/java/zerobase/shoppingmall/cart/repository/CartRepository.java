package zerobase.shoppingmall.cart.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import zerobase.shoppingmall.cart.dto.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, String> {

    Optional<Cart> findByUserId(String userId);

    Optional<Cart> findByUserIdAndProductId(String userId, Long productId);


    @Transactional
    void deleteByUserIdAndProductId(String userId, Long productId);

    //@Transactional
    void deleteAllByUserId(String userId);

    Optional<List<Cart>> findAllByUserId(String userId);
}
