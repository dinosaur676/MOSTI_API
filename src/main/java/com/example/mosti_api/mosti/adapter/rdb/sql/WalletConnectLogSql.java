package com.example.mosti_api.mosti.adapter.rdb.sql;

public class WalletConnectLogSql {
    static public String selectAll = "select * from wallet_connect_log where public_key = ?";
    static public String insert = "insert into wallet_connect_log(connect_name, connect_description, connect_url, public_key, connect_time) " +
            "values(?, ?, ?, ?, ?)";
}
