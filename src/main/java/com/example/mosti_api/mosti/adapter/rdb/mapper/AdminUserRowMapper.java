package com.example.mosti_api.mosti.adapter.rdb.mapper;


import com.example.mosti_api.mosti.application.domain.AdminUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUserRowMapper implements RowMapper<AdminUser> {
    @Override
    public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AdminUser adminUser = new AdminUser(rs.getString("uid"));
        adminUser.setName(rs.getString("name"));
        adminUser.setPassword(rs.getString("password"));
        adminUser.setEmail(rs.getString("email"));
        adminUser.setStatus(rs.getString("status"));
        adminUser.setCreatedOn(rs.getTimestamp("created_on").toLocalDateTime());

        return adminUser;
    }
}
