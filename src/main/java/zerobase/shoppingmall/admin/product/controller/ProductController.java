package zerobase.shoppingmall.admin.product.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.shoppingmall.admin.product.dto.ProductInput;
import zerobase.shoppingmall.admin.product.service.ProductService;
import zerobase.shoppingmall.response.BaseResponse;

@AllArgsConstructor
@RestController
@RequestMapping("/company")
public class ProductController {
    //관리자가 상품 등록, 조회, 삭제, 수정

    private final ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<Object> createProduct(@RequestBody ProductInput productInput) {

        if(productInput == null){
            throw new RuntimeException();
        }

        BaseResponse response = productService.register(productInput);
        return ResponseEntity.ok(response);
    }

}
