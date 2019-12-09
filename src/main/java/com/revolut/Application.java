package com.revolut;

import com.revolut.servlet.account.AccountServlet;
import com.revolut.servlet.transfer.TransferServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Application {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(getServletHandler());
        server.start();
        server.join();
    }

    private static ServletHandler getServletHandler() {
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(AccountServlet.class, "/accounts");
        servletHandler.addServletWithMapping(TransferServlet.class, "/accounts/transfer");
        return servletHandler;
    }
}
