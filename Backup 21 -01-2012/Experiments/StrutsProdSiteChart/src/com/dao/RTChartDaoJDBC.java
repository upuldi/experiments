package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/2/11
 * Time: 6:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class RTChartDaoJDBC extends JdbcTemplate implements RTChartDao {

    public ResultSet getRTValues(String type, String date) {

        String query = "select Concat(DATE_FORMAT(date,'%Y-%m-%d'),' ',time) as DT, REPLACE(FORMAT(vol,0),',',''), " +
                "TRIM(close) from upuldi_CSEDAILYDATA.RT_DATA where ticker = ? and date = ? order by id desc;";
        Object[] parameters = new Object[2];
        parameters[0] = type;
        parameters[1] = date;


        SqlRowSet rowSet = this.queryForRowSet(query,parameters);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();

    }

    public ResultSet getRTindexValues(String type, String date) {


        String query = null;

        if ("ASPI".equalsIgnoreCase(type)) {

            query = "select concat(DATE_FORMAT(date,'%Y-%m-%d'),' ',time) as date, ASPI as value\n" +
                    "from upuldi_CSEDAILYDATA.RT_INDEX_DATA\n" +
                    "where date = ? order by id desc;";
        }
        if ("MPI".equalsIgnoreCase(type)) {

            query = "select concat(DATE_FORMAT(date,'%Y-%m-%d'),' ',time) as date, MPI as value\n" +
                    "from upuldi_CSEDAILYDATA.RT_INDEX_DATA\n" +
                    "where date = ? order by id desc;";
        }


        Object[] parameters = new Object[1];
        parameters[0] = date;

        SqlRowSet rowSet = this.queryForRowSet(query,parameters);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();
    }
}
