package com.example.mosti_api.mosti.application.dto.request;

public record AdminUserUpdatedReqDto(
        String id,
        String name,
        String email,
        String password,
        String status
) {
}
