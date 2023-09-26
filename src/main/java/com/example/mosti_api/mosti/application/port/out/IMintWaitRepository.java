package com.example.mosti_api.mosti.application.port.out;


import com.example.mosti_api.mosti.application.domain.MintWait;

import java.util.List;

public interface IMintWaitRepository {
    List<MintWait> select(String userName);
    MintWait selectByTokenId(String userName, long tokenId);
    void insert(String userName, long tokenId);
    void update(String userName, long tokenId);
}
