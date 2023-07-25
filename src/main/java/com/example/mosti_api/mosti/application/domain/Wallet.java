package com.example.mosti_api.mosti.application.domain;

public class Wallet {

    private long id;
    private String userName;
    private String walletName;
    private String walletTag;
    private boolean enable;

    public Wallet(long id, String userName, String walletName, String walletTag, boolean enable) {
        this.id = id;
        this.userName = userName;
        this.walletName = walletName;
        this.walletTag = walletTag;
        this.enable = enable;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getWalletName() {
        return walletName;
    }

    public String getWalletTag() {
        return walletTag;
    }

    public boolean isEnable() {
        return enable;
    }
}
