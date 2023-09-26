package com.example.mosti_api.mosti.application;

import com.example.mosti_api.framework.exception.DomainException;
import com.example.mosti_api.framework.helper.Do;
import com.example.mosti_api.mosti.adapter.blockchain.Gateway;
import com.example.mosti_api.mosti.adapter.blockchain.GatewayResponse;
import com.example.mosti_api.mosti.application.domain.TokenInfo;
import com.example.mosti_api.mosti.application.domain.UserToken;
import com.example.mosti_api.mosti.application.port.in.IGatewayService;
import com.example.mosti_api.mosti.application.port.in.ITokenInfoService;
import com.example.mosti_api.mosti.application.port.in.IUserTokenService;
import com.example.mosti_api.mosti.application.port.out.ITokenInfoRepository;
import com.example.mosti_api.mosti.application.port.out.IUserTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class GatewayService implements IGatewayService {

    private Gateway gateWay;

    public GatewayService(Gateway gateWay) {
        this.gateWay = gateWay;
    }

    @Override
    public String onwerOfInPublic(long tokenId) {
        Map<String, String> param = new HashMap<>();
        param.put("tokenId", String.valueOf(tokenId));

        GatewayResponse gatewayResponse = gateWay.requestWithPostWebClient(Gateway.API.ADMIN_OWNER, param);
        return gatewayResponse.getData("owner");
    }

    @Override
    public int balanceOfInPublic(String to, long tokenId) {
        Map<String, String> param = new HashMap<>();
        param.put("to", to);
        param.put("tokenId", String.valueOf(tokenId));

        GatewayResponse gatewayResponse = gateWay.requestWithPostWebClient(Gateway.API.ADMIN_BALANCE, param);
        return Integer.parseInt(gatewayResponse.getData("balance"));
    }

    @Override
    public GatewayResponse createTokenInPublic(String tokenOwner, long tokenType, String metaData) {
        Map<String, String> param = new HashMap<>();
        param.put("tokenOwner", tokenOwner);
        param.put("data", metaData);

        GatewayResponse gatewayResponse = gateWay.requestWithPostWebClient(Gateway.API.ADMIN_CREATE_TOKEN, param);


        return gatewayResponse;
    }

    @Override
    public GatewayResponse mintTokenInPublic(String toAddress, long tokenId, LocalDateTime deletedOn) {
        Map<String, String> param = new HashMap<>();

        param.put("to", toAddress);
        param.put("tokenId", String.valueOf(tokenId));

        GatewayResponse gatewayResponse = gateWay.requestWithPostWebClient(Gateway.API.ADMIN_MINT_TOKEN, param);

        return gatewayResponse;
    }

    @Override
    public GatewayResponse burnTokenInPublic(String tokenOwner, String toAddress, long tokenId) {
        Map<String, String> param = new HashMap<>();

        param.put("tokenOwner", tokenOwner);
        param.put("to", toAddress);
        param.put("tokenId", String.valueOf(tokenId));

        GatewayResponse gatewayResponse = gateWay.requestWithPostWebClient(Gateway.API.ADMIN_BURN_TOKEN, param);

        return gatewayResponse;
    }
}
