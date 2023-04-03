package sejong.europlanner.service.serviceinterface;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface NaverService {
    String getAccessToken(String code, String state);

    JsonNode getUserProfile(String accessToken) throws IOException;

    ResponseEntity<String> logout(String accessToken);
}