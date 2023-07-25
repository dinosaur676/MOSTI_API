package com.example.mosti_api.mosti.adapter.rdb;

import com.example.mosti_api.framework.jdbc.JdbcCommand;
import com.example.mosti_api.framework.jdbc.JdbcQuery;
import com.example.mosti_api.mosti.adapter.rdb.mapper.WalletRowMapper;
import com.example.mosti_api.mosti.adapter.rdb.sql.WalletRepositorySql;
import com.example.mosti_api.mosti.application.domain.Wallet;
import com.example.mosti_api.mosti.application.port.out.IWalletRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WalletRepository implements IWalletRepository {
    private final JdbcCommand jdbcCommand;
    private final JdbcQuery<Wallet> jdbcQuery;

    public WalletRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcCommand = new JdbcCommand(jdbcTemplate);
        this.jdbcQuery = new JdbcQuery<>(jdbcTemplate, new WalletRowMapper());
    }
    @Override
    public void delete(String userName, String walletName, String walletTag) {
        jdbcCommand.실행(WalletRepositorySql.delete, userName, walletName, walletTag);
    }

    @Override
    public List<Wallet> selectAll(String userName) {
        return jdbcQuery.목록조회(WalletRepositorySql.selectAll, userName);
    }

    @Override
    public List<Wallet> selectAllByWalletName(String walletName) {
        return jdbcQuery.목록조회(WalletRepositorySql.selectAllByWalletName, walletName);
    }

    @Override
    public Wallet select(String userName, String walletName, String walletTag) {
        return jdbcQuery.조회(WalletRepositorySql.select, userName, walletName, walletTag);
    }

    @Override
    public void create(String userName, String walletName, String walletTag) {
        jdbcCommand.실행(WalletRepositorySql.create, userName, walletName, walletTag);
    }
}
