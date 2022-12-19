package zerobase.shoppingmall.admin.product.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.shoppingmall.admin.product.dto.ProductInput;
import zerobase.shoppingmall.admin.product.dto.entity.Product;
import zerobase.shoppingmall.admin.product.service.ProductService;

@AllArgsConstructor
@RestController
public class ProductController {
    //관리자가 상품 등록, 조회, 상세조회, 삭제, 수정
    private final ProductService productService;

    @PostMapping("/register/product")
    public ResponseEntity<Object> createProduct(@RequestBody ProductInput productInput) {

        if(productInput == null){
            throw new RuntimeException();
        }

        Product product = productService.register(productInput);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product")
    public ResponseEntity<Object> productList(Pageable pageable) {
        Page<Product> products = productService.getAllProduct(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/detail")
    public ResponseEntity<Object> productDetail(@RequestParam Long productId) {
        Product product = productService.getProductDetail(productId);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/product")
    public ResponseEntity<Object> deleteProduct(@RequestParam Long productId) {
        Product product = productService.deleteProduct(productId);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/product")
    public ResponseEntity<Object> updateProduct(@RequestParam Long productId,
        @RequestBody ProductInput productInput) {
        Product product = productService.updateProduct(productId,productInput);

        return ResponseEntity.ok(product);
    }
}
