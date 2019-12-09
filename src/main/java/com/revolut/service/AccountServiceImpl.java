package com.revolut.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.conf.DataSource;
import com.revolut.dao.AccountDao;
import com.revolut.servlet.account.AccountJson;
import com.revolut.servlet.transfer.TransferJson;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final DataSource dataSource;
    private final ObjectMapper objectMapper;

    public AccountServiceImpl(AccountDao accountDao, DataSource dataSource, ObjectMapper objectMapper) {
        this.accountDao = accountDao;
        this.dataSource = dataSource;
        this.objectMapper = objectMapper;
    }

    @Override
    public Collection<AccountJson> findAll() {
        try {
            return accountDao.findAll()
                    .stream()
                    .map(e -> objectMapper.convertValue(e, AccountJson.class))
                    .collect(toList());
        } catch (SQLException e) {
            throw new RuntimeException("Can't call database", e);
        }
    }

    @Override
    public void transferMoney(TransferJson transferJson) {
        String sqlFrom = "UPDATE ACCOUNT SET BALANCE = BALANCE - " + transferJson.getAmount() + " WHERE ACCOUNT_ID = " + transferJson.getAccountIdFrom();
        String sqlTo = "UPDATE ACCOUNT SET BALANCE = BALANCE + " + transferJson.getAmount() + " WHERE ACCOUNT_ID = " + transferJson.getAccountIdTo();
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);  // start a transaction
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sqlFrom);
                stmt.executeUpdate(sqlTo);
            } catch (SQLException e) {
                conn.rollback();    // rollback a transaction
                throw e;
            }
            conn.commit();  // end a transaction
        } catch (SQLException e) {
            throw new RuntimeException("Can't update database", e);
        }
    }
}
