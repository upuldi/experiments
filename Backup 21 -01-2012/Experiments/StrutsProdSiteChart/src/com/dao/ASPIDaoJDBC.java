package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/30/11
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ASPIDaoJDBC extends JdbcTemplate implements ASPIDao{


    public ResultSet getASPIValues() {

        String query = "select  DATE_FORMAT(date,'%Y-%m-%d') as date ,open,high,low,close,vol from upuldi_CSEDAILYDATA.EOD_DATA where ticker =  'BFL.N0000'";

        SqlRowSet rowSet = this.queryForRowSet(query);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();
    }
}
