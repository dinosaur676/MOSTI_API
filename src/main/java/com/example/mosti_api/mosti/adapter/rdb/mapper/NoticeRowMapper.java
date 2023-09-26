package com.example.mosti_api.mosti.adapter.rdb.mapper;

import com.example.mosti_api.mosti.application.domain.Notice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoticeRowMapper implements RowMapper<Notice> {
    @Override
    public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Notice.Builder.builder()
                .setId(rs.getLong("id"))
                .setTitle(rs.getString("title"))
                .setWriter(rs.getString("writer"))
                .setContent(rs.getString("content"))
                .setCreatedOn(rs.getTimestamp("created_on").toLocalDateTime())
                .build();
    }
}
