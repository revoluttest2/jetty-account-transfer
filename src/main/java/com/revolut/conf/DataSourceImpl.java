package com.revolut.conf;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceImpl implements DataSource {

    private static final javax.sql.DataSource ds = JdbcConnectionPool.create("jdbc:h2:mem:sample;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'", "sa", "sa");

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
