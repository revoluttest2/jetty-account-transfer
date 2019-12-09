package com.revolut.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.dao.AccountDao;
import com.revolut.dao.AccountDaoImpl;
import com.revolut.service.AccountService;
import com.revolut.service.AccountServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private static final Map<Class, Object> map;

    static {
        map = new HashMap<>();
        map.put(ObjectMapper.class, new ObjectMapper());
        map.put(DataSource.class, new DataSourceImpl());
        map.put(AccountDao.class, new AccountDaoImpl(getObject(DataSource.class)));
        map.put(AccountService.class, new AccountServiceImpl(getObject(AccountDao.class), getObject(DataSource.class), getObject(ObjectMapper.class)));
    }

    public static <T> T getObject(Class<T> clazz) {
        return (T) map.get(clazz);
    }
}
