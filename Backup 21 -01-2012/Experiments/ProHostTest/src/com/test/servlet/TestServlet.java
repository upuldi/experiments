package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/9/11
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.write("TEST SERVLET WORKING.....");
    }
}
