package zerobase.shoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ShoppingmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingmallApplication.class, args);
    }

}
