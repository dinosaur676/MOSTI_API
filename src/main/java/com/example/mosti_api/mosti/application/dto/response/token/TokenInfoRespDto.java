package com.example.mosti_api.mosti.application.dto.response.token;

import com.example.mosti_api.mosti.adapter.ramda.dto.response.RamdaMintResponseDto;
import com.example.mosti_api.mosti.application.domain.TokenInfo;

public record TokenInfoRespDto (
        long tokenId,
        String metaData,
        String userName
){
    static public TokenInfoRespDto 생성(TokenInfo tokenInfo) {
        return new TokenInfoRespDto(
                tokenInfo.getTokenId(),
                tokenInfo.getMetaData(),
                tokenInfo.getUserName()
        );
    }
}
