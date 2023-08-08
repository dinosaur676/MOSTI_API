package com.example.mosti_api.mosti.application.dto.request.wallet;

public record WalletConnectLogReqDto(
        String name,
        String description,
        String url,
        String publicKey
) {
}
