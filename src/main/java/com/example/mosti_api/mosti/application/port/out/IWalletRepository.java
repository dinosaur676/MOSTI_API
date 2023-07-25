package com.example.mosti_api.mosti.application.port.out;

import com.example.mosti_api.mosti.application.domain.Wallet;

import java.util.List;

public interface IWalletRepository {
    void delete(String userName, String walletName, String walletTag);
    List<Wallet> selectAll(String userName);
    List<Wallet> selectAllByWalletName(String walletName);
    Wallet select(String userName, String walletName, String walletTag);
    void create(String userName, String walletName, String walletTag);
}
