package com.revolut.dao;

import com.revolut.conf.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class AccountDaoImpl implements AccountDao {

    private final DataSource dataSource;

    public AccountDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Collection<AccountEntity> findAll() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT")) {

            Collection<AccountEntity> collection = new ArrayList<>();
            while (rs.next()) {
                collection.add(new AccountEntity(rs.getLong("ACCOUNT_ID"), rs.getBigDecimal("BALANCE")));
            }
            return collection;
        }
    }

    @Override
    public AccountEntity findById(Long accountId) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT WHERE ACCOUNT_ID = " + accountId)) {
            return rs.next() ? new AccountEntity(rs.getLong("ACCOUNT_ID"), rs.getBigDecimal("BALANCE")) : null;
        }
    }
}
