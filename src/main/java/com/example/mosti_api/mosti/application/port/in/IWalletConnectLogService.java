package com.example.mosti_api.mosti.application.port.in;

import com.example.mosti_api.mosti.application.domain.WalletConnectLog;
import com.example.mosti_api.mosti.application.dto.response.wallet.WalletConnectLogRespDto;

import java.util.List;

public interface IWalletConnectLogService {

    List<WalletConnectLogRespDto> select(String publicKey);
    void insert(WalletConnectLog walletConnectLog);
}
