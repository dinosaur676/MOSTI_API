package com.example.mosti_api.mosti.adapter.rdb;

import com.example.mosti_api.framework.jdbc.JdbcCommand;
import com.example.mosti_api.framework.jdbc.JdbcQuery;
import com.example.mosti_api.mosti.adapter.rdb.mapper.NoticeRowMapper;
import com.example.mosti_api.mosti.adapter.rdb.sql.NoticeSql;
import com.example.mosti_api.mosti.application.domain.Notice;
import com.example.mosti_api.mosti.application.port.out.INoticeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeRepository implements INoticeRepository {

    private final JdbcCommand jdbcCommand;
    private final JdbcQuery<Notice> jdbcQuery;

    public NoticeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcCommand = new JdbcCommand(jdbcTemplate);
        this.jdbcQuery = new JdbcQuery<>(jdbcTemplate, new NoticeRowMapper());
    }

    @Override
    public List<Notice> 목록조회() {
        return jdbcQuery.목록조회(NoticeSql.목록조회);
    }

    @Override
    public List<Notice> 조회(String writer) {
        return jdbcQuery.목록조회(NoticeSql.목록조회, writer);
    }

    @Override
    public void 추가(Notice notice) {
        jdbcCommand.실행(NoticeSql.추가, notice.getWriter(), notice.getTitle(), notice.getContent(), notice.getCreatedOn());
    }

    @Override
    public void 삭제(Notice notice) {
        jdbcCommand.실행(NoticeSql.삭제, notice.getId());
    }

    @Override
    public void 수정(Notice notice) {
        jdbcCommand.실행(NoticeSql.수정, notice.getContent(), notice.getId());
    }
}
