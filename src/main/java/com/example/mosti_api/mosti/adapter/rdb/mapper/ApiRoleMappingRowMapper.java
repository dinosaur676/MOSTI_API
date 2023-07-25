package com.example.mosti_api.mosti.adapter.rdb.mapper;

import com.example.mosti_api.mosti.application.domain.ApiRoleMapping;
import com.example.mosti_api.mosti.application.domain.builder.ApiFindBuilder;
import com.example.mosti_api.mosti.application.domain.builder.ApiRoleMappingFindBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApiRoleMappingRowMapper implements RowMapper<ApiRoleMapping> {
    @Override
    public ApiRoleMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ApiRoleMappingFindBuilder.anApiRoleMapping(rs.getInt("id"))
                .withApi(
                    ApiFindBuilder.anApi(rs.getInt("api_id"))
                            .withMethod(rs.getString("method"))
                            .withPath(rs.getString("path"))
                            .withName(rs.getString("name"))
                            .build()
                ).build();
    }
}
