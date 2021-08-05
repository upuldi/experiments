package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/4/11
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODChartDaoJDBC extends JdbcTemplate implements EODChartDao{


    public ResultSet getEODValues(String type) {

       // String query = "SELECT DATE_FORMAT( DATE,  '%Y-%m-%d' ) DATE, REPLACE( FORMAT( vol, 0 ) ,  ',',  '' ) VOL, TRIM( close ) , REPLACE( trade,  ',',  '' ) AS trade FROM upuldi_CSEDAILYDATA.EOD_DATA WHERE ticker =  ? ORDER BY STR_TO_DATE(DATE,'%Y%m%d') DESC;";
        String query = "SELECT DATE_FORMAT( DATE,  '%Y-%m-%d' ) DATE, REPLACE( FORMAT( vol, 0 ) ,  ',',  '' ) VOL, TRIM( close ) , REPLACE( trade,  ',',  '' ) AS trade FROM upuldi_CSEDAILYDATA.EOD_DATA WHERE ticker =  ? ORDER BY id DESC;";
        Object[] parameters = new Object[1];
        parameters[0] = type;

        SqlRowSet rowSet = this.queryForRowSet(query,parameters);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();
    }

    public ResultSet getEODValuesForCandle(String type) {

        String query = "select DATE_FORMAT(date,'%Y-%m-%d') Date,TRIM(open) as open,TRIM(high) as high,TRIM(low) as low,TRIM(close) as close,REPLACE(FORMAT(vol,0),',','') as vol, REPLACE( trade,  ',',  '' ) as trade from upuldi_CSEDAILYDATA.EOD_DATA where ticker = ? order by id desc; ";
        Object[] parameters = new Object[1];
        parameters[0] = type;

        SqlRowSet rowSet = this.queryForRowSet(query,parameters);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();
    }

    public ResultSet getEODForignHoldingValues(String stock) {

        String query = "SELECT DATE_FORMAT(STR_TO_DATE(S.LAST_TRADED_DATE,'%d/%m/%Y'),  '%Y-%m-%d') DATE, REPLACE(RTRIM(LTRIM(S.FORIGN_HOLDING)) ,  ',', '' ) VOL \n" +
                "FROM upuldi_CSEDAILYDATA.DAILY_STOCK_STATISTICS S\n" +
                "INNER JOIN upuldi_CSEDAILYDATA.COMPANY_STOCK_MAPPING M on S.COMPANY_NAME = M.COMPANY_NAME\n" +
                "WHERE M.TICKER =  ? \n" +
                "group by LAST_TRADED_DATE,FORIGN_HOLDING\n" +
                "ORDER BY STR_TO_DATE(LAST_TRADED_DATE,'%d/%m/%Y') DESC;";

        Object[] parameters = new Object[1];
        parameters[0] = stock;

        SqlRowSet rowSet = this.queryForRowSet(query,parameters);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();

    }

    public ResultSet getEODIssuedQtyValues(String stock) {

        String query = "SELECT DATE_FORMAT(STR_TO_DATE(S.LAST_TRADED_DATE,'%d/%m/%Y'),  '%Y-%m-%d') DATE, REPLACE(RTRIM(LTRIM(S.ISSUED_QTY)) ,  ',', '' ) QTY\n" +
                "FROM upuldi_CSEDAILYDATA.DAILY_STOCK_STATISTICS S\n" +
                "INNER JOIN upuldi_CSEDAILYDATA.COMPANY_STOCK_MAPPING M on S.COMPANY_NAME = M.COMPANY_NAME\n" +
                "WHERE M.TICKER =  ? \n" +
                "ORDER BY STR_TO_DATE(LAST_TRADED_DATE,'%d/%m/%Y') DESC;";

        Object[] parameters = new Object[1];
        parameters[0] = stock;

        SqlRowSet rowSet = this.queryForRowSet(query,parameters);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();

    }
}
