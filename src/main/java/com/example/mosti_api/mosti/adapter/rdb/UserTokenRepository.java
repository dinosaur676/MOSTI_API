package com.example.mosti_api.mosti.adapter.rdb;

import com.example.mosti_api.framework.jdbc.JdbcCommand;
import com.example.mosti_api.framework.jdbc.JdbcQuery;
import com.example.mosti_api.mosti.adapter.rdb.mapper.UserTokenRowMapper;
import com.example.mosti_api.mosti.adapter.rdb.mapper.WalletRowMapper;
import com.example.mosti_api.mosti.adapter.rdb.sql.UserTokenRepositorySql;
import com.example.mosti_api.mosti.application.domain.UserToken;
import com.example.mosti_api.mosti.application.domain.Wallet;
import com.example.mosti_api.mosti.application.port.out.IUserTokenRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public class UserTokenRepository implements IUserTokenRepository {

    private final JdbcCommand jdbcCommand;
    private final JdbcQuery<UserToken> jdbcQuery;

    public UserTokenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcCommand = new JdbcCommand(jdbcTemplate);
        this.jdbcQuery = new JdbcQuery<>(jdbcTemplate, new UserTokenRowMapper());
    }
    @Override
    public List<UserToken> select(String walletName, String walletTag, String userName) {
        return jdbcQuery.목록조회(UserTokenRepositorySql.select, userName, walletName, walletTag);
    }

    @Override
    public void delete(String userName, String walletName, String walletTag, long tokenId) {
        jdbcCommand.실행(UserTokenRepositorySql.delete, userName, walletName, walletTag, tokenId);
    }

    @Override
    public void insert(String userName, String walletName, String walletTag, long tokenId, LocalDateTime createdOn, LocalDateTime deletedOn) {
        jdbcCommand.실행(UserTokenRepositorySql.insert, tokenId, walletName, walletTag, userName, createdOn, deletedOn);
    }
}
