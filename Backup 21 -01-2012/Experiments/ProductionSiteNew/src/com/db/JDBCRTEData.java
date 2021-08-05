package com.db;

import com.domain.RTData;
import com.domain.RTIndexValues;
import com.framework.SpringContext;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 4/29/11
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class JDBCRTEData extends JdbcTemplate {

    private Logger log = Logger.getLogger(JDBCRTEData.class);


    /**
     * Save CSE Data..
     *
     * @param rtData
     */
    public void saveCSEData(RTData rtData) {

        log.info("Adding a new trade....");
        Connection con = null;

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String db = properties.getProperty("jdbc.db");
        String rtDataTable = properties.getProperty("jdbc.rtdata.table");


        try {

            String insertQuery = "INSERT INTO " + db + "." + rtDataTable + " (`ID`, `TICKER`, `DATE`, `TIME`, `OPEN`, `HIGH`, `LOW`, `CLOSE`,`VOL`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? )";

            this.update(insertQuery, new Object[]{rtData.getId(), rtData.getTicker(), rtData.getDate(), rtData.getTime(), rtData.getOpen(), rtData.getHigh
                    (), rtData.getLow(), rtData.getClose(), rtData.getVol()});


        } catch (Exception e) {
            log.error("ERROR OCCURED DURRING DB OPERATION.... " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void saveIndexData(RTIndexValues rtIndexData) {

        log.info("Adding a new index data....");
        Connection con = null;

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String db = properties.getProperty("jdbc.db");
        String rtIndexDataTable = properties.getProperty("jdbc.rt_index.table");

        try {

            String insertQuery = "INSERT INTO "+ db + "." + rtIndexDataTable +"(`ID`,`DATE`,`TIME`,`ASPI`,`MPI`)\n" +
                    "VALUES(?,?,?,?,?);";
            this.update(insertQuery, new Object[]{rtIndexData.getId(), rtIndexData.getDate(), rtIndexData.getTime(), rtIndexData.getASPI(), rtIndexData.getMPI()});

        } catch (Exception e) {
            log.error("ERROR OCCURED DURRING DB OPERATION.... " + e.getMessage());
            e.printStackTrace();
        }
    }
}
