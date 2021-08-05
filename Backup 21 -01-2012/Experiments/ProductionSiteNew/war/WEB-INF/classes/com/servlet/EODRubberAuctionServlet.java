package com.servlet;

import com.framework.SpringContext;
import com.job.eod.EODRubberPrice;
import com.job.eod.EODTradingSummery;
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
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODRubberAuctionServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(EODRubberAuctionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        EODRubberPrice eODRubberPrice = (EODRubberPrice) SpringContext.getBean("eODRubberAuction");
        PrintWriter out = resp.getWriter();
        try {

            eODRubberPrice.processRubberAuctionPrices();
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
