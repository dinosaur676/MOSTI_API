package com.example.mosti_api.mosti.application.port.out;

import com.example.mosti_api.mosti.application.domain.TokenInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface ITokenInfoRepository {
    List<TokenInfo> select(String userName);
    TokenInfo selectByTokenId(long tokenId);
    void insert(long tokenId, String metaData, String userName, LocalDateTime createdOn);
}
