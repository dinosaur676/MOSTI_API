package com.example.mosti_api.mosti.application.port.in;

import com.example.mosti_api.mosti.application.domain.WalletConnectLog;
import com.example.mosti_api.mosti.application.dto.request.wallet.WalletConnectLogReqDto;
import com.example.mosti_api.mosti.application.dto.response.wallet.WalletConnectLogRespDto;

import java.util.List;

public interface IWalletConnectLogRepository {

    List<WalletConnectLog> select(String publicKey);
    void insert(WalletConnectLog dto);
}
