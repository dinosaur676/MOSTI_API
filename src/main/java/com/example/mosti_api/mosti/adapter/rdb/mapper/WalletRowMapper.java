package com.example.mosti_api.mosti.adapter.rdb.mapper;

import com.example.mosti_api.mosti.application.domain.User;
import com.example.mosti_api.mosti.application.domain.Wallet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletRowMapper implements RowMapper<Wallet> {
    @Override
    public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Wallet(
                rs.getLong("id"),
                rs.getString("user_name"),
                rs.getString("wallet_name"),
                rs.getString("wallet_tag"),
                rs.getBoolean("enable")
        );
    }
}
