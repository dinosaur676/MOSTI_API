package com.example.mosti_api.mosti.adapter.rdb.mapper;

import com.example.mosti_api.mosti.application.domain.Wallet;
import com.example.mosti_api.mosti.application.domain.WalletConnectLog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletConnectLogRowMapper implements RowMapper<WalletConnectLog> {
    @Override
    public WalletConnectLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new WalletConnectLog(
                rs.getString("connect_name"),
                rs.getString("connect_description"),
                rs.getString("connect_url"),
                rs.getString("public_key"),
                rs.getTimestamp("connect_time").toLocalDateTime()
        );
    }
}
