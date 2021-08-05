package com.site.action.amchart;

import au.com.bytecode.opencsv.CSVWriter;
import com.dao.RTChartDao;
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
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class RealTimeDataCSVAction extends ActionSupport {

    private static Logger log = Logger.getLogger(RealTimeDataCSVAction.class);

    private InputStream fileInputStream;

    private RTChartDao rtChartDao;

    private String param;

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public void setRtChartDao(RTChartDao rtChartDao) {
        this.rtChartDao = rtChartDao;
    }


    public String getRealTimeData() {


        try {

            String[] paramArray = param.split("[|]");
            String type = paramArray[0];
            String date = paramArray[1];

            byte[] blobData = getRealtimeCSVData(type,date);
            fileInputStream = new ByteArrayInputStream(blobData);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return SUCCESS;

    }


    public String getRealTimeIndexData() {





        return SUCCESS;

    }


    private byte[] getRealtimeCSVData(String type,String date) throws IOException, SQLException {

        StringWriter stringWriter = new StringWriter();

        CSVWriter writer = new CSVWriter(stringWriter);
        ResultSet rset = rtChartDao.getRTValues(type,date);
        log.debug("Result Set is coming .. " + rset);
        writer.writeAll(rset, false);
        writer.close();
        String csvStringContent = stringWriter.toString().replace("\"", "");

        return csvStringContent.getBytes();
    }
}