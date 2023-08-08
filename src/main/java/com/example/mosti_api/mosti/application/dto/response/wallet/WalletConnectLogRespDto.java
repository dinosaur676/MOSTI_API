package com.example.mosti_api.mosti.application.dto.response.wallet;

import com.example.mosti_api.mosti.application.domain.WalletConnectLog;

import java.time.format.DateTimeFormatter;

public record WalletConnectLogRespDto(
        String name,
        String description,
        String url,
        String publicKey,
        String time
) {
    static public WalletConnectLogRespDto create(WalletConnectLog log) {
        return new WalletConnectLogRespDto(
                log.getName(),
                log.getDescription(),
                log.getUrl(),
                log.getPublicKey(),
                log.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}
