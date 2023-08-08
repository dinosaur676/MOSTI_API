package com.example.mosti_api.mosti.adapter.rdb;

import com.example.mosti_api.framework.jdbc.JdbcCommand;
import com.example.mosti_api.framework.jdbc.JdbcQuery;
import com.example.mosti_api.mosti.adapter.rdb.mapper.WalletConnectLogRowMapper;
import com.example.mosti_api.mosti.adapter.rdb.mapper.WalletRowMapper;
import com.example.mosti_api.mosti.adapter.rdb.sql.WalletConnectLogSql;
import com.example.mosti_api.mosti.application.domain.Wallet;
import com.example.mosti_api.mosti.application.domain.WalletConnectLog;
import com.example.mosti_api.mosti.application.dto.request.wallet.WalletConnectLogReqDto;
import com.example.mosti_api.mosti.application.port.in.IWalletConnectLogRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WalletConnectLogRepository implements IWalletConnectLogRepository {
    private final JdbcCommand jdbcCommand;
    private final JdbcQuery<WalletConnectLog> jdbcQuery;

    public WalletConnectLogRepository(JdbcTemplate jdbcTemplate)  {
        this.jdbcCommand = new JdbcCommand(jdbcTemplate);
        this.jdbcQuery = new JdbcQuery<>(jdbcTemplate, new WalletConnectLogRowMapper());
    }

    @Override
    public List<WalletConnectLog> select(String publicKey) {
        return jdbcQuery.목록조회(WalletConnectLogSql.selectAll, publicKey);
    }

    @Override
    public void insert(WalletConnectLog dto) {
        jdbcCommand.실행(WalletConnectLogSql.insert, dto.getName(), dto.getDescription(), dto.getUrl(), dto.getPublicKey(), dto.getTime());
    }
}
