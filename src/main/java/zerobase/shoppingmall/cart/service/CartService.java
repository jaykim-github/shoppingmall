package zerobase.shoppingmall.cart.service;

import zerobase.shoppingmall.cart.dto.CartInput;
import zerobase.shoppingmall.cart.dto.entity.Cart;

public interface CartService {

    Cart addCart(CartInput cartInput);

    String deleteCartProudct(CartInput cartInput);
}
