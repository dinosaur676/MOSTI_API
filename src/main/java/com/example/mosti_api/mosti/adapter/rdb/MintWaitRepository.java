package com.example.mosti_api.mosti.adapter.rdb;

import com.example.mosti_api.framework.jdbc.JdbcCommand;
import com.example.mosti_api.framework.jdbc.JdbcQuery;
import com.example.mosti_api.mosti.adapter.rdb.mapper.MintWaitRowMapper;
import com.example.mosti_api.mosti.adapter.rdb.sql.MintWaitRepositorySql;
import com.example.mosti_api.mosti.application.domain.MintWait;
import com.example.mosti_api.mosti.application.port.out.IMintWaitRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MintWaitRepository implements IMintWaitRepository {
    private final JdbcCommand jdbcCommand;
    private final JdbcQuery<MintWait> jdbcQuery;

    public MintWaitRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcCommand = new JdbcCommand(jdbcTemplate);
        this.jdbcQuery = new JdbcQuery<>(jdbcTemplate, new MintWaitRowMapper());
    }

    @Override
    public List<MintWait> select(String userName) {
        return jdbcQuery.목록조회(MintWaitRepositorySql.select, userName);
    }

    @Override
    public MintWait selectByTokenId(String userName, long tokenId) {
        return jdbcQuery.조회(MintWaitRepositorySql.selectByTokenId, userName, tokenId);
    }

    @Override
    public void insert(String userName, long tokenId) {
        jdbcCommand.실행(MintWaitRepositorySql.insert, userName, tokenId, LocalDateTime.now());
    }

    @Override
    public void update(String userName, long tokenId) {
        jdbcCommand.실행(MintWaitRepositorySql.update, tokenId, userName);
    }
}
