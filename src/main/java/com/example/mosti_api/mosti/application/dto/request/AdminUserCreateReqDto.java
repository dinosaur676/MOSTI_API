package com.example.mosti_api.mosti.application.dto.request;

public record AdminUserCreateReqDto(
        String name,
        String password,
        String email
){}
