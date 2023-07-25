package com.example.mosti_api.mosti.adapter.rdb.sql;

public class WalletRepositorySql {
    static public String selectAll = "select * from wallet_table where user_name = ? and enable = 1";
    static public String selectAllByWalletName = "select * from wallet_table where wallet_name = ?";
    static public String select = "select * from wallet_table where user_name = ? and wallet_name = ? and wallet_tag = ?";
    static public String delete = "update wallet_table set enable = 0 where user_name = ? and wallet_name = ? and wallet_tag = ?";
    static public String create = "insert into wallet_table(user_name, wallet_name, wallet_tag, enable) values(?, ?, ?, true)";
}
