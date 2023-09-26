package com.example.mosti_api.mosti.adapter.api;


import com.example.mosti_api.framework.dto.ResponseDto;
import com.example.mosti_api.framework.dto.SuccessRespDto;
import com.example.mosti_api.mosti.application.domain.WalletConnectLog;
import com.example.mosti_api.mosti.application.dto.request.wallet.WalletConnectLogReqDto;
import com.example.mosti_api.mosti.application.dto.response.wallet.WalletConnectLogRespDto;
import com.example.mosti_api.mosti.application.port.in.IWalletConnectLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/wallet-connect-log")
@RestController
public class WalletConnectLogController {
    private final IWalletConnectLogService walletConnectLogService;

    public WalletConnectLogController(IWalletConnectLogService walletConnectLogService) {
        this.walletConnectLogService = walletConnectLogService;
    }

    @PostMapping
    public ResponseDto select(@RequestBody WalletConnectLogReqDto dto) {

        List<WalletConnectLogRespDto> respDtoList = walletConnectLogService.select(dto.publicKey());

        if(respDtoList == null || respDtoList.isEmpty())
            return new SuccessRespDto("내역이 없습니다.");

        return new SuccessRespDto(respDtoList, "조회가 성공적으로 완료되었습니다.");
    }

    @PutMapping
    public ResponseDto insert(@RequestBody WalletConnectLogReqDto dto) {
        walletConnectLogService.insert(new WalletConnectLog(dto.name(), dto.description(), dto.url(), dto.publicKey()));

        return new SuccessRespDto("추가가 완료되었습니다.");
    }

}
