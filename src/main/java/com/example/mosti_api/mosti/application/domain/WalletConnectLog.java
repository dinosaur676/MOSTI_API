package com.example.mosti_api.mosti.application.domain;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class WalletConnectLog {
    String name;
    String description;
    String url;
    String publicKey;
    LocalDateTime time;

    public WalletConnectLog(String name, String description, String url, String publicKey, LocalDateTime time) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.publicKey = publicKey;
        this.time = time;
    }

    public WalletConnectLog(String name, String description, String url, String publicKey) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.publicKey = publicKey;
        this.time = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
