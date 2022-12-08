package zerobase.shoppingmall.response;

import java.util.List;
import jodd.net.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {

    private String message;
    private Integer count;
    private List<Object> result;
}
