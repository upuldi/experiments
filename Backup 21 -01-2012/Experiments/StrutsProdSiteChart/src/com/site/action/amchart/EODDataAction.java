package com.site.action.amchart;

import au.com.bytecode.opencsv.CSVWriter;
import com.dao.EODChartDao;
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
 * Date: 11/17/11
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODDataAction extends ActionSupport {

    private static Logger log = Logger.getLogger(EODDataAction.class);

    private String type;

    private EODChartDao eodChartDao;

    private InputStream fileInputStream;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setEodChartDao(EODChartDao eodChartDao) {
        this.eodChartDao = eodChartDao;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public String getEODChartData() {

        try {

            byte[] blobData = getEODDataCSV(type);
            fileInputStream = new ByteArrayInputStream(blobData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String getEODCandleData() {

        try {

            byte[] blobData = getEODCandleCSV(type);
            fileInputStream = new ByteArrayInputStream(blobData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String getEODForeignHolding() {

        try {

            byte[] blobData = getEODForignHoldingCSV(type);
            fileInputStream = new ByteArrayInputStream(blobData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String getEODIssuedQty() {

        try {

            byte[] blobData = getEODIssuedQtyCSV(type);
            fileInputStream = new ByteArrayInputStream(blobData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }


    private byte[] getEODDataCSV(String type) throws IOException, SQLException {

        StringWriter stringWriter = new StringWriter();

        CSVWriter writer = new CSVWriter(stringWriter);
        ResultSet rset = eodChartDao.getEODValues(type);
        log.debug("Result Set is coming : " + rset);
        writer.writeAll(rset, false);
        writer.close();
        String csvStringContent = stringWriter.toString().replace("\"", "");

        return csvStringContent.getBytes();
    }

    private byte[] getEODCandleCSV(String type) throws IOException, SQLException {

        StringWriter stringWriter = new StringWriter();

        CSVWriter writer = new CSVWriter(stringWriter);
        ResultSet rset = eodChartDao.getEODValuesForCandle(type);
        log.debug("Result Set is coming : " + rset);
        writer.writeAll(rset, false);
        writer.close();
        String csvStringContent = stringWriter.toString().replace("\"", "");

        return csvStringContent.getBytes();
    }

    private byte[] getEODForignHoldingCSV(String stock) throws IOException, SQLException {

        StringWriter stringWriter = new StringWriter();

        CSVWriter writer = new CSVWriter(stringWriter);
        ResultSet rset = eodChartDao.getEODForignHoldingValues(stock);
        log.debug("Result Set is coming : " + rset);
        writer.writeAll(rset, false);
        writer.close();
        String csvStringContent = stringWriter.toString().replace("\"", "");

        return csvStringContent.getBytes();
    }

     private byte[] getEODIssuedQtyCSV(String stock) throws IOException, SQLException {

        StringWriter stringWriter = new StringWriter();

        CSVWriter writer = new CSVWriter(stringWriter);
        ResultSet rset = eodChartDao.getEODIssuedQtyValues(stock);
        log.debug("Result Set is coming : " + rset);
        writer.writeAll(rset, false);
        writer.close();
        String csvStringContent = stringWriter.toString().replace("\"", "");

        return csvStringContent.getBytes();
    }

}
