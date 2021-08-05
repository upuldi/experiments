package com.site.action.amchart;

import au.com.bytecode.opencsv.CSVWriter;
import com.dao.GoldDao;
import com.dao.RubberDao;
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
 * Date: 11/18/11
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommodityDataCVSAction extends ActionSupport {

    private static Logger log = Logger.getLogger(CommodityDataCVSAction.class);
    private RubberDao rubberDao;
    private String type;
    private InputStream fileInputStream;
    private GoldDao goldDao;

    public void setGoldDao(GoldDao goldDao) {
        this.goldDao = goldDao;
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

    public void setRubberDao(RubberDao rubberDao) {
        this.rubberDao = rubberDao;
    }

    public String getRubberCSVData() {

        try {
            byte[] blobData = getRubberDataCSV(type);
            fileInputStream = new ByteArrayInputStream(blobData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public String getGoldCSVData() {

        try {
            byte[] blobData = getGoldDataCSV(type);
            fileInputStream = new ByteArrayInputStream(blobData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    private byte[] getGoldDataCSV(String type) throws IOException, SQLException {

        StringWriter stringWriter = new StringWriter();
        CSVWriter writer = new CSVWriter(stringWriter,';');

        ResultSet rset = goldDao.getGoldValues(type);
        log.debug("Result Set is coming .... " + rset);
        writer.writeAll(rset, false);
        writer.close();
        String csvStringContent = stringWriter.toString().replace("\"", "");
        return csvStringContent.getBytes();
    }

    private byte[] getRubberDataCSV(String type) throws IOException, SQLException {

        StringWriter stringWriter = new StringWriter();

        CSVWriter writer = new CSVWriter(stringWriter);

        ResultSet rset = rubberDao.getRubberValues(type);

        log.debug("Result Set is coming .... " + rset);
        writer.writeAll(rset, false);
        writer.close();
        String csvStringContent = stringWriter.toString().replace("\"", "");


        return csvStringContent.getBytes();
    }

}
