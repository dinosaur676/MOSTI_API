package com.example.mosti_api.mosti.application.port.in;


import com.example.mosti_api.mosti.adapter.blockchain.GatewayResponse;
import com.example.mosti_api.mosti.application.domain.TokenInfo;
import com.example.mosti_api.mosti.application.domain.UserToken;

import java.time.LocalDateTime;

public interface IGatewayService {


    String onwerOfInPublic(long tokenId);
    int balanceOfInPublic(String to, long tokenId);
    GatewayResponse createTokenInPublic(String tokenOwner, long tokenType, String metaData);
    GatewayResponse mintTokenInPublic(String toAddress, long tokenId, LocalDateTime deletedOn);
    GatewayResponse burnTokenInPublic(String tokenOwner, String toAddress, long tokenId);

}
