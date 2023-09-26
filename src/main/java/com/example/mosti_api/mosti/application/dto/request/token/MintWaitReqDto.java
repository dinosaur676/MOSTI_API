package com.example.mosti_api.mosti.application.dto.request.token;

public record MintWaitReqDto(

        String address,
        String userName,
        long tokenId

) {}
