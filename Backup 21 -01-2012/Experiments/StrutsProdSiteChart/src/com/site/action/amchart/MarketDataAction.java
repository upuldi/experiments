package com.site.action.amchart;

import au.com.bytecode.opencsv.CSVWriter;
import com.dao.MarketSummeryDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/15/11
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketDataAction extends ActionSupport {

    private static Logger log = Logger.getLogger(MarketDataAction.class);

    private MarketSummeryDao marketSummeryDao;
    private InputStream fileInputStream;

    private String type;

    public void setMarketSummeryDao(MarketSummeryDao marketSummeryDao) {
        this.marketSummeryDao = marketSummeryDao;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarketSummeryData() {


        try {

            byte[] blobData = getMarketSummeryCSV(type);
            fileInputStream = new ByteArrayInputStream(blobData);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return SUCCESS;

    }


    private byte[] getMarketSummeryCSV(String type) throws IOException, SQLException {

        StringWriter stringWriter = new StringWriter();
        CSVWriter writer = new CSVWriter(stringWriter);

        ResultSet rset = marketSummeryDao.getMarketSummeryValues(type);
        log.debug("Result Set is coming.." + rset);

        writer.writeAll(rset, false);
        writer.close();
        String csvStringContent = stringWriter.toString().replace("\"", "");
        return csvStringContent.getBytes();
    }

}
