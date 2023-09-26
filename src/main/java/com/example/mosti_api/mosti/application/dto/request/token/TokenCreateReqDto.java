package com.example.mosti_api.mosti.application.dto.request.token;

public record TokenCreateReqDto(
        String owner,
        String comment
) {
}
