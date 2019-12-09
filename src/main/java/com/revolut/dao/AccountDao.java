package com.revolut.dao;

import java.sql.SQLException;
import java.util.Collection;

public interface AccountDao {

    AccountEntity findById(Long accountId) throws SQLException;
    Collection<AccountEntity> findAll() throws SQLException;
}
