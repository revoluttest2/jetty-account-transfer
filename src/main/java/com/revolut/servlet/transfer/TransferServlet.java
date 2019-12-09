package com.revolut.servlet.transfer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.conf.Context;
import com.revolut.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransferServlet extends HttpServlet {

    private final AccountService accountService = Context.getObject(AccountService.class);
    private final ObjectMapper objectMapper = Context.getObject(ObjectMapper.class);

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TransferJson transferJson = objectMapper.readValue(req.getReader(), TransferJson.class);
        accountService.transferMoney(transferJson);
    }
}
