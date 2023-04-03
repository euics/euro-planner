package sejong.europlanner.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.europlanner.service.serviceImpl.KakaoServiceImpl;
import sejong.europlanner.service.serviceinterface.KakaoService;
import sejong.europlanner.vo.response.ResponseKakaoUser;
import sejong.europlanner.vo.response.ResponseNaverUser;

import java.io.IOException;

@RestController
@RequestMapping("/kakao")
@Slf4j
public class KakaoController {
    private final KakaoService kakaoService;

    @Autowired
    public KakaoController(KakaoServiceImpl kakaoService) {
        this.kakaoService = kakaoService;
    }

    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    @GetMapping("/login")
    public ResponseEntity<ResponseKakaoUser> kakaoCallback(@RequestParam String code) throws Exception {
        JsonNode userProfile = kakaoService.getUserFromCode(code);
        ResponseKakaoUser responseKakaoUser = kakaoService.setToken(kakaoService.toResponse(userProfile));

        return ResponseEntity.ok().body(responseKakaoUser);
    }

    @GetMapping("/user")
    public String kakaoGetUser(@RequestParam String code) throws Exception {
        JsonNode userProfile = kakaoService.getUserFromCode(code);

        return userProfile.toString();
    }
}
