package zerobase.shoppingmall.admin.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInput {
    private String productName;
    private String productDescription;

    private Long price;

    //0: 판매중지, 1:판매중
    private int status;
}
