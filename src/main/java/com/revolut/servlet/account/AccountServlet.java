package com.revolut.servlet.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.conf.Context;
import com.revolut.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServlet extends HttpServlet {

    private final AccountService accountService = Context.getObject(AccountService.class);
    private final ObjectMapper objectMapper = Context.getObject(ObjectMapper.class);

    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(objectMapper.writeValueAsString(accountService.findAll()));
    }
}
