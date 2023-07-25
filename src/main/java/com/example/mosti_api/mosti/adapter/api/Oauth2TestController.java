package com.example.mosti_api.mosti.adapter.api;

import com.example.mosti_api.framework.dto.SuccessRespDto;
import com.example.mosti_api.mosti.adapter.keycloak.KeycloakGateway;
import com.example.mosti_api.mosti.application.KeycloakService;
import com.example.mosti_api.mosti.application.domain.Api;
import com.example.mosti_api.mosti.application.dto.request.user.UserCreateReqDto;
import com.example.mosti_api.mosti.application.dto.request.user.UserUpdateReqDto;
import com.example.mosti_api.mosti.application.dto.response.OAuthUserRespDto;
import com.example.mosti_api.mosti.application.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class Oauth2TestController {

    private final KeycloakGateway keycloakGateway;
    private final KeycloakService keycloakService;
    private final Logger logger = LoggerFactory.getLogger(Oauth2TestController.class);

    public Oauth2TestController(KeycloakGateway keycloakGateway, KeycloakService keycloakService) {
        this.keycloakGateway = keycloakGateway;
        this.keycloakService = keycloakService;
    }
    @PutMapping("/user")
    public SuccessRespDto createUser(@AuthenticationPrincipal Jwt token, JwtAuthenticationToken principal, @RequestBody UserCreateReqDto userCreateReqDto) {
        keycloakService.create(userCreateReqDto);

        return new SuccessRespDto(
                new OAuthUserRespDto(token.getClaimAsString("sub"), token.getClaimAsString("email") , null), "회원조회가 성공적으로 완료되었습니다.");
    }

    @PostMapping("/user")
    public SuccessRespDto updateUser(@AuthenticationPrincipal Jwt token, JwtAuthenticationToken principal, @RequestBody UserUpdateReqDto userUpdateReqDto) {
        keycloakService.modifyUser(JWTUtil.getUserId(token), userUpdateReqDto.userName());

        return new SuccessRespDto(
            new OAuthUserRespDto(token.getClaimAsString("sub"), token.getClaimAsString("email") , null), "회원조회가 성공적으로 완료되었습니다.");
    }

    @DeleteMapping("/user")
    public SuccessRespDto deleteUser(@AuthenticationPrincipal Jwt token, JwtAuthenticationToken principal) {
        keycloakService.remove(JWTUtil.getUserId(token));

        return new SuccessRespDto(
                new OAuthUserRespDto(token.getClaimAsString("sub"), token.getClaimAsString("email") , null), "회원조회가 성공적으로 완료되었습니다.");
    }

    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
    }


}
