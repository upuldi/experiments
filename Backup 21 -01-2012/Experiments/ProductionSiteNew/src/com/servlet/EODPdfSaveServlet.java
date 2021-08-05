package com.servlet;

import com.framework.SpringContext;
import com.util.PDFSave;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/26/11
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class EODPdfSaveServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(EODPdfSaveServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug("Starting servlet..");

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String cseDailyUrl = properties.getProperty("cse.daily.pdf");
        String cseWeeklyUrl = properties.getProperty("cse.weekly.pdf");
        String pdfSavingLocationDaily = properties.getProperty("pdf.save.daily.location");
        String pdfSavingLocationWeekly = properties.getProperty("pdf.save.weekly.location");

        PDFSave.savePDF(cseDailyUrl,pdfSavingLocationDaily);
        PDFSave.savePDF(cseWeeklyUrl,pdfSavingLocationWeekly);

        resp.getWriter().println("Sucess");


    }
}
