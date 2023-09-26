package com.example.mosti_api.mosti.application.dto.response.token;

import com.example.mosti_api.mosti.application.domain.UserToken;

public record UserTokenRespDto(
        long tokenId,
        String metaData
) {
    public static UserTokenRespDto 생성(UserToken userToken) {
        return new UserTokenRespDto(
                userToken.getTokenId(),
                userToken.getMetaData()
        );
    }
}
