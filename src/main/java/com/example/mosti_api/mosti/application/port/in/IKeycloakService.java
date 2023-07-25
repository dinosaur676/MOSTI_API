package com.example.mosti_api.mosti.application.port.in;

import com.example.mosti_api.mosti.application.dto.request.user.UserCreateReqDto;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IKeycloakService {
    public void modifyUser(String userId, String userName);
    void remove(String userId);
    void create(UserCreateReqDto userCreateReqDto);
}
