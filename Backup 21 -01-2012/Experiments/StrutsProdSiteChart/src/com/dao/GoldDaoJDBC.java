package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 1/19/12
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoldDaoJDBC extends JdbcTemplate implements GoldDao {


    public ResultSet getGoldValues(String type) {

        String query = "select  DATE_FORMAT(date,'%Y-%m-%d') as date, REPLACE("+ type +",',','') \n" +
                "from upuldi_CSEDAILYDATA.GOLD \n" +
                "order by date  desc";
        SqlRowSet rowSet = this.queryForRowSet(query);

        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();

    }
}
