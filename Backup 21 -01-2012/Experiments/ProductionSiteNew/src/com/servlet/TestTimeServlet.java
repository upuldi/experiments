package com.servlet;

import com.util.UniversalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/19/11
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestTimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Date nowNotLocalized = new Date();
        Date nowLocalized  = UniversalTime.getLocalTime();

        PrintWriter out = resp.getWriter();
        out.println(" NOT LOCALIZED TIME IS : " + nowNotLocalized);
        out.println(" LOCALIZED TIME IS : " + nowLocalized);

    }
}
