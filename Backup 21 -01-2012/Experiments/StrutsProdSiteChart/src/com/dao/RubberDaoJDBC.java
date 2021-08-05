package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/31/11
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class RubberDaoJDBC extends JdbcTemplate implements RubberDao {

    public ResultSet getRubberValues(String type) {

//        String query = "select  DATE_FORMAT(date,'%Y-%m-%d') as date,price_LKR from upuldi_CSEDAILYDATA.RUBBER_AUCTION_PRICE where item = '" + type + "' order by date  desc";
//        SqlRowSet rowSet = this.queryForRowSet(query);

        String query = "select  DATE_FORMAT(date,'%Y-%m-%d') as date,price_LKR from upuldi_CSEDAILYDATA.RUBBER_AUCTION_PRICE where item = ? and price_LKR <> '0.00' order by date  desc";
        Object[] parameters = new Object[1];
        parameters[0] = type;
        SqlRowSet rowSet = this.queryForRowSet(query,parameters);


        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();
    }
}
