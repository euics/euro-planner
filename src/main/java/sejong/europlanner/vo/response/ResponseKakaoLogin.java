package sejong.europlanner.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseKakaoLogin {
    private String accessToken;

    private String refreshToken;
}
