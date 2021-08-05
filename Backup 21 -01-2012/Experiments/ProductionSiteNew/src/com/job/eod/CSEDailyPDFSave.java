package com.job.eod;

import com.framework.SpringContext;
import com.util.PDFSave;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/26/11
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class CSEDailyPDFSave {

    private static Logger log = Logger.getLogger(CSEDailyPDFSave.class);

    public String processPDFSave() throws IOException {

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String cseDailyUrl = properties.getProperty("cse.daily.pdf");
        String pdfDailySavingLocation = properties.getProperty("pdf.save.daily.location");

        PDFSave.savePDF(cseDailyUrl, pdfDailySavingLocation);
        return "SUCCESS";
    }
}
