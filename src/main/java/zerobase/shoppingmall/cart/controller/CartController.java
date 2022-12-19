package zerobase.shoppingmall.cart.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.shoppingmall.cart.dto.CartInput;
import zerobase.shoppingmall.cart.dto.entity.Cart;
import zerobase.shoppingmall.cart.service.CartService;

@RestController
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/user/cart")
    public ResponseEntity<Object> addCart(@RequestBody CartInput cartInput) {

        Cart cart = cartService.addCart(cartInput);

        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/user/cart")
    public ResponseEntity<Object> deleteCart(@RequestBody CartInput cartInput){
        String proudctName = cartService.deleteCartProudct(cartInput);

        return ResponseEntity.ok(proudctName);
    }
}
