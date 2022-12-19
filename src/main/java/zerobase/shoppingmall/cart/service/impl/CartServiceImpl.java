package zerobase.shoppingmall.cart.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.shoppingmall.admin.product.dto.entity.Product;
import zerobase.shoppingmall.admin.product.repository.ProductRepository;
import zerobase.shoppingmall.cart.dto.CartInput;
import zerobase.shoppingmall.cart.dto.entity.Cart;
import zerobase.shoppingmall.cart.service.CartService;
import zerobase.shoppingmall.cart.repository.CartRepository;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart addCart(CartInput cartInput) {
        //카트값을 찾아서, 있으면 이미 있다는 값 리턴, 없으면 추가
        Optional<Cart> optionalCart = cartRepository.findByUserIdAndProductId(cartInput.getUserId(),
            cartInput.getProductId());

        if (optionalCart.isPresent()) {
            throw new RuntimeException("이미 카트에 담긴 상품입니다.");
        }

       Product product = productRepository.findById(cartInput.getProductId()).orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        Cart cart = Cart.builder()
            .userId(cartInput.getUserId())
            .productId(product.getProductId())
            .productName(product.getProductName())
            .price(product.getPrice())
            .countProduct(cartInput.getCount())
            .build();

        return cartRepository.save(cart);
    }

    @Override
    public String deleteCartProudct(CartInput cartInput) {
        Cart cart = cartRepository.findByUserIdAndProductId(cartInput.getUserId(),
            cartInput.getProductId()).orElseThrow(() -> new RuntimeException("장바구니 상품이 없습니다."));

        cartRepository.deleteByUserIdAndProductId(cartInput.getUserId(), cartInput.getProductId());

        return cart.getProductName();
    }
}
