package com.example.mosti_api.mosti.adapter.keycloak;

import com.example.mosti_api.mosti.adapter.ramda.dto.response.RamdaErrorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class KeycloakGateway {
    private final String BASE_URL;
    private final WebClient webClient;
    @Autowired
    public KeycloakGateway(@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")String baseUrl
        ){
        this.BASE_URL = baseUrl;
        this.webClient = WebClient.builder()
                                  .baseUrl(BASE_URL)
                                  .defaultHeaders(httpHeaders -> {
                                      httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                                      httpHeaders.add(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE);
                                  })
                                  .build();
    }

    public void requestWithWebClient(API api, Map<String, String> param) {
        String deleteURI = "";

        if(param.containsKey("delete"))
        {
            deleteURI = "/" + param.get("delete");
        }

        WebClient.RequestBodySpec bodySpec = this.webClient.mutate()
                .build()
                .method(api.getMethod())
                .uri(api.getUri() + deleteURI);


        bodySpec.retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(
                        s -> {
                            System.out.println("responseBody : " + s);
                        }
                )
                .doOnError(throwable -> {
                    System.out.println(throwable.getMessage());
                });
    }

    private static <T> Mono<ResponseEntity<T>> processResponse(Class<T> classType, ClientResponse clientResponse) {
        if(clientResponse.statusCode().is2xxSuccessful()){
            return clientResponse.toEntity(classType);
        }
        else{
            return clientResponse.bodyToMono(RamdaErrorResponseDto.class).flatMap(e -> Mono.error(new RuntimeException(e.getMessage())));
        }
    }

    public enum API {
        USER_INFO("/protocol/openid-connect/userinfo", HttpMethod.GET,""),
        USER_DELETE("/users/", HttpMethod.DELETE, "");

        private String uri;
        private HttpMethod method;
        private String jsonFormat;

        public String getUri() {
            return uri;
        }

        public HttpMethod getMethod() {
            return method;
        }

        public String getJsonFormat() {
            return jsonFormat;
        }
        API(String uri, HttpMethod method, String jsonFormat) {
            this.uri = uri;
            this.method = method;
            this.jsonFormat = jsonFormat;
        }
    }

}
