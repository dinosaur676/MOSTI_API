package com.example.mosti_api.mosti.application.dto.response.token;


import com.example.mosti_api.mosti.application.domain.MintWait;

import java.time.LocalDateTime;

public record MintWaitRespDto(
        long tokenId,
        LocalDateTime createdOn
) {
    public static MintWaitRespDto 생성(MintWait mintWait) {
        return new MintWaitRespDto(
                mintWait.getTokenId(),
                mintWait.getCreatedOn()
        );
    }
}
