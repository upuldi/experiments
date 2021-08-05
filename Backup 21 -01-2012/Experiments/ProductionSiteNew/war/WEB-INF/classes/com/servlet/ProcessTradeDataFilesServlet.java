package com.servlet;

import com.datasetup.ProcessTradeDataFiles;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 10/10/11
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProcessTradeDataFilesServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(ProcessTradeDataFilesServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        log.debug("Start process trade data files ................... ");
        ProcessTradeDataFiles processTradeDataFiles = new ProcessTradeDataFiles();
        processTradeDataFiles.processTradeDataFiles();

        resp.getWriter().println("Success .......................");






    }

}
