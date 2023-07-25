package com.example.mosti_api.mosti.application;

import com.example.mosti_api.mosti.application.domain.Wallet;
import com.example.mosti_api.mosti.application.dto.request.wallet.WalletReqDto;
import com.example.mosti_api.mosti.application.port.in.IWalletService;
import com.example.mosti_api.mosti.application.port.out.IWalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService implements IWalletService {

    private final IWalletRepository walletRepository;

    public WalletService(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void delete(String userName, WalletReqDto dto) {
        walletRepository.delete(userName, dto.walletName(), dto.walletTag());
    }

    @Override
    public void create(String userName, WalletReqDto dto) {
        walletRepository.create(userName, dto.walletName(), dto.walletTag());
    }

    @Override
    public List<Wallet> selectAll(String userName) {
        return walletRepository.selectAll(userName);
    }

    @Override
    public List<Wallet> selectAllByWalletName(String walletName) {
        return walletRepository.selectAllByWalletName(walletName);
    }

    @Override
    public Wallet select(String userName, WalletReqDto dto) {
        return null;
    }
}
