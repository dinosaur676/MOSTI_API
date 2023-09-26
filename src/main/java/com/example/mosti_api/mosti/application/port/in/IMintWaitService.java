package com.example.mosti_api.mosti.application.port.in;


import com.example.mosti_api.mosti.application.dto.response.token.MintWaitRespDto;

import java.util.List;

public interface IMintWaitService {
    List<MintWaitRespDto> select(String userName);
    MintWaitRespDto selectByTokenId(String userName, long tokenId);
    void insert(String userName, long tokenId);
    void update(String userName, long tokenId);
}
