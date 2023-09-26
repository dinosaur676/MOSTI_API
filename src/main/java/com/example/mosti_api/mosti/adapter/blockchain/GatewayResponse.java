package com.example.mosti_api.mosti.adapter.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class GatewayResponse {


    private Map<String, Object> jsonMapper = null;

    public GatewayResponse(String json) {
        try {
            jsonMapper = new ObjectMapper().readValue(json, Map.class);
        } catch (JsonProcessingException e) {

        }
    }

    public String getData(String label) {

        Map<String, String> dataMap = (Map<String, String>)jsonMapper.get("data");

        return dataMap.get(label);
    }

    public String getMessage() {
        return (String) jsonMapper.get("message");
    }

    public String getStatusCode() {
        return (String) jsonMapper.get("statusCode");
    }


}
