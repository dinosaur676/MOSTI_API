package com.example.mosti_api.mosti.application.port.in;

import com.example.mosti_api.mosti.application.domain.TokenInfo;
import com.example.mosti_api.mosti.application.dto.response.token.TokenInfoRespDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ITokenInfoService {
    List<TokenInfoRespDto> select(String userName);
    TokenInfoRespDto selectByTokenId(long tokenId);
    void insert(long tokenId, String metaData, String userName, LocalDateTime createdOn);
}
