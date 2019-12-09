package com.revolut.service;

import com.revolut.servlet.account.AccountJson;
import com.revolut.servlet.transfer.TransferJson;

import java.util.Collection;

public interface AccountService {

    Collection<AccountJson> findAll();
    void transferMoney(TransferJson transferJson);
}
