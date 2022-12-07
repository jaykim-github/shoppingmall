package zerobase.shoppingmall.admin.product.service;

import zerobase.shoppingmall.admin.product.dto.ProductInput;
import zerobase.shoppingmall.response.BaseResponse;

public interface ProductService {

    public BaseResponse register(ProductInput productInput);
}
