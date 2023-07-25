package com.example.mosti_api.mosti.application.port.in;

import com.example.mosti_api.mosti.application.domain.Wallet;
import com.example.mosti_api.mosti.application.dto.request.wallet.WalletReqDto;

import java.util.List;

public interface IWalletService {
    void delete(String userName, WalletReqDto dto);
    void create(String userName, WalletReqDto dto);
    List<Wallet> selectAll(String userName);
    List<Wallet> selectAllByWalletName(String walletName);
    Wallet select(String userName, WalletReqDto dto);
}
