package com.example.mosti_api.mosti.application.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.mosti_api.framework.dto.SuccessRespDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

public class Oauth2LogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(mapper.writeValueAsString(new SuccessRespDto("ok","요청이 완료되었습니다.")));
    }
}
