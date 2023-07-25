package com.example.mosti_api.mosti.adapter.api;

import com.example.mosti_api.framework.dto.ResponseDto;
import com.example.mosti_api.framework.dto.SuccessRespDto;
import com.example.mosti_api.mosti.application.domain.Wallet;
import com.example.mosti_api.mosti.application.dto.request.wallet.WalletReqDto;
import com.example.mosti_api.mosti.application.dto.response.wallet.WalletRespDto;
import com.example.mosti_api.mosti.application.port.in.IWalletService;
import com.example.mosti_api.mosti.application.util.JWTUtil;
import com.example.mosti_api.mosti.application.util.WalletUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequestMapping("/api/wallet")
@RestController
public class WalletController {

    private final IWalletService walletService;

    public WalletController(IWalletService walletService) {
        this.walletService = walletService;
    }

    @PutMapping
    public ResponseDto createAvatar(@AuthenticationPrincipal Jwt jwt, @RequestBody WalletReqDto walletReqDto) {

        String email = JWTUtil.getEmail(jwt);
        String walletTag = "";
        String walletName = walletReqDto.walletName();

        List<Wallet> walletList = walletService.selectAllByWalletName(walletName);
        List<String> tagList = walletList.stream().map(e -> e.getWalletTag()).collect(Collectors.toList());

        Set<String> tagSet = new HashSet<>(tagList);

        while(true) {
            walletTag = WalletUtil.randomTag();

            if(!tagSet.contains(walletTag))
                break;
        }



        walletService.create(email, new WalletReqDto(walletName, walletTag));

        return new SuccessRespDto(new WalletRespDto(walletName, walletTag), "생성이 완료되었습니다.");
    }

    @DeleteMapping
    public ResponseDto deleteWallet(@AuthenticationPrincipal Jwt jwt, @RequestBody WalletReqDto walletReqDto) {

        String email = JWTUtil.getEmail(jwt);
        walletService.delete(email, walletReqDto);
        
        return new SuccessRespDto(new WalletRespDto(walletReqDto.walletName(), walletReqDto.walletTag()), "삭제가 완료되었습니다.");
    }
}
