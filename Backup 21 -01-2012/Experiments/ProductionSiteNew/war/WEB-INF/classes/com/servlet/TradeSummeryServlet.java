package com.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeSummeryServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(TradeSummeryServlet.class);

    public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {


        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        URL cseStockOverview = new URL("http://www.cse.lk/sectors/tradeSummaryMarket.htm");
        URLConnection urlConnection = cseStockOverview.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        String fileString = "";
        int lineNo = 0;

        while ((inputLine = bufferedReader.readLine()) != null) {

           // log.debug(lineNo + " Input line " + inputLine);
            if (lineNo > 116 && lineNo < 603) {
                //log.debug(" Reading input line " + inputLine);
                out.println(inputLine.replaceAll("$", ""));
            }
            lineNo++;
        }
        bufferedReader.close();
        out.close();


    }

}
