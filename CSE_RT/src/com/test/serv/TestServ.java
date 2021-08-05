package com.test.serv;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: epsi
 * Date: 21-Dec-2010
 * Time: 19:47:50
 * To change this template use File | Settings | File Templates.
 */
public class TestServ extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        test(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       test(request,response);
    }

    private void test(HttpServletRequest request, HttpServletResponse response) throws IOException {

                    PrintWriter out;
                  out = response.getWriter();
        out.write("TEST"); 

    }


}
