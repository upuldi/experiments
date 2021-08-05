package com.servlet;

import com.framework.SpringContext;
import com.job.eod.EODMarketData;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/11/11
 * Time: 6:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODMarketDataServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(EODMarketDataServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        log.info("Executing EODMarketDayData Job");

        EODMarketData eodMarketData = (EODMarketData) SpringContext.getBean("eodMarketData");
        PrintWriter out = resp.getWriter();

        try {

            eodMarketData.processEODMarketData();
            out.write("Success.....");

        } catch (ParseException e) {
            e.printStackTrace();
            out.write("ERROR.....");
        } catch (IOException e) {
            e.printStackTrace();
            out.write("ERROR.....");
        }


    }


}
