package com.example.mosti_api.mosti.application;

import com.example.mosti_api.mosti.application.domain.WalletConnectLog;
import com.example.mosti_api.mosti.application.dto.response.wallet.WalletConnectLogRespDto;
import com.example.mosti_api.mosti.application.port.out.IWalletConnectLogRepository;
import com.example.mosti_api.mosti.application.port.in.IWalletConnectLogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletConnectLogService implements IWalletConnectLogService {
    private final IWalletConnectLogRepository walletConnectLogRepository;

    public WalletConnectLogService(IWalletConnectLogRepository walletConnectLogRepository) {
        this.walletConnectLogRepository = walletConnectLogRepository;
    }

    @Override
    public List<WalletConnectLogRespDto> select(String publicKey) {
        return walletConnectLogRepository.select(publicKey).stream().map(WalletConnectLogRespDto::create).collect(Collectors.toList());
    }

    @Override
    public void insert(WalletConnectLog walletConnectLog) {
        walletConnectLogRepository.insert(walletConnectLog);
    }
}
