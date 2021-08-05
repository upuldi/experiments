package com.servlet;

import com.framework.SpringContext;
import com.job.eod.EodTrades;
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
 * Date: 8/18/11
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODTradeDataServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(EODMarketDataServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        log.info("Executing EOD Trade Data Job");

        EodTrades eodTrades = (EodTrades) SpringContext.getBean("eodTrades");
        PrintWriter out = resp.getWriter();

        try {
            String jobStatus = eodTrades.processEodTrades();
            out.write("Success....." + jobStatus);
        } catch (ParseException e) {
            e.printStackTrace();
            out.write("ERROR.....");
        } catch (IOException e) {
            e.printStackTrace();
            out.write("ERROR.....");
        }
    }
}