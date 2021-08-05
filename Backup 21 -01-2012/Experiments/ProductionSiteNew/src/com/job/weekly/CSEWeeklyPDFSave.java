package com.job.weekly;

import com.framework.SpringContext;
import com.util.PDFSave;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/26/11
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class CSEWeeklyPDFSave {

    private static Logger log = Logger.getLogger(CSEWeeklyPDFSave.class);


    public String processPDFSave() throws IOException {

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String cseWeeklyUrl = properties.getProperty("cse.weekly.pdf");
        String pdfSavingLocation = properties.getProperty("pdf.save.weekly.location");

        PDFSave.savePDF(cseWeeklyUrl, pdfSavingLocation);
        return "SUCCESS";

    }


}
