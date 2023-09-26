package com.example.mosti_api.mosti.application.dto.request.token;

public record UserTokenReqDto(
        String address,
        String walletName,
        String walletTag,
        long tokenId
) {
}
