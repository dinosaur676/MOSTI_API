package com.example.mosti_api.mosti.application;

import com.example.mosti_api.mosti.application.dto.request.user.UserCreateReqDto;
import com.example.mosti_api.mosti.application.port.in.IKeycloakService;
import com.example.mosti_api.mosti.application.util.Credentials;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KeycloakService implements IKeycloakService {
    private final Keycloak keycloak;
    private static final String REALM_NAME = "mosti-dev";

    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public void modifyUser(String userId, String userName) {
        UsersResource usersResource =  keycloak.realm(REALM_NAME).users();
        UserResource resource = usersResource.get(userId);
        UserRepresentation user = resource.toRepresentation();
        user.setUsername("userTest@emblock.co.kr");
        user.setLastName(userName);
        user.setFirstName(userName);
        resource.update(user);


    }

    @Override
    public void remove(String userId) {
        UsersResource usersResource =  keycloak.realm(REALM_NAME).users();
        UserResource resource = usersResource.get(userId);

        resource.remove();
    }

    @Override
    public void create(UserCreateReqDto userCreateReqDto) {
        UsersResource usersResource =  keycloak.realm(REALM_NAME).users();

        CredentialRepresentation credentialRepresentation = Credentials.createPasswordCredentials(userCreateReqDto.password());

        UserRepresentation user = new UserRepresentation();
        user.setFirstName(userCreateReqDto.firstName());
        user.setLastName(userCreateReqDto.lastName());
        user.setEmail(userCreateReqDto.email());
        user.setUsername(userCreateReqDto.userName());
        user.setCredentials(Collections.singletonList(credentialRepresentation));
        user.setEnabled(true);

        usersResource.create(user);
    }
}
