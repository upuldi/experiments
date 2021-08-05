package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/18/11
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeDaoJDBC extends JdbcTemplate implements TradeDao {


    public ResultSet getTradeValuesForTheDay(String type, String date) {


        String query = "select * from (SELECT price , sum(tradevol) vol , count(*) trades FROM `upuldi_CSEDAILYDATA`.`STOCK_TRADES` " +
                "where date = ? and ticker = ? group by price) X order by X.vol desc;";
        Object[] parameters = new Object[2];
        parameters[0] = date;
        parameters[1] = type;

        SqlRowSet rowSet = this.queryForRowSet(query,parameters);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();

    }

    public ResultSet getTradeValuesForSelectedDays(String type, String date) {

        String query = "select * from (SELECT price , sum(tradevol) vol , count(*) trades FROM `upuldi_CSEDAILYDATA`.`STOCK_TRADES` where date in (select x.date from (select @rownum:=@rownum+1 ‘row’, t.date from `upuldi_CSEDAILYDATA`.`STOCK_TRADES`  t, (SELECT @rownum:=0) r group by date order by date desc limit 3 )x) and ticker = ? group by price) X order by X.vol desc";
        Object[] parameters = new Object[2];
        parameters[0] = date;
        parameters[1] = type;

        SqlRowSet rowSet = this.queryForRowSet(query,parameters);
        ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
        return resultSetWrappingSqlRowSet.getResultSet();


    }
}
