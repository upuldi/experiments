package com.db;

import com.domain.Trade;
import com.framework.SpringContext;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 4/29/11
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class JDBCTRADEData extends JdbcTemplate {


    private static Logger log = Logger.getLogger(JDBCTRADEData.class);


    /**
     * This method is used to insert the trade count and understand whether a trade has been
     * happned durring the time. If the trade has been happned it will return the trade volumen
     * during the time. If it returns the same value as the previous volume there has not been
     * any trade happned durring that time.
     *
     *
     * @param trade
     * @return
     */
    public Trade getTradeCount(Trade trade) {

        Connection con = null;

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String db = properties.getProperty("jdbc.db");
        String dailyTradeTable = properties.getProperty("jdbc.dailytrade.table");

        Trade returnTrade = new Trade();
        returnTrade.setDate(trade.getDate());
        returnTrade.setTicker(trade.getTicker());
        returnTrade.setTradeCount(trade.getTradeCount());

        long previousTradeCount = 0L;
        long previousVol = 0L;

        long newTradeCount = trade.getTradeCount();
        long newVol = trade.getLastVol();

        try {

            try {

//                con = getJDBCConnection();
//                String findSql = "select * from  "+ db +"."+ dailyTradeTable +" t " +
//                        "where t.date = ? and t.ticker = ? ;";
//
//                PreparedStatement prepGetTrade = con.prepareStatement(findSql);
//
//                log.info("SQL : " + findSql + " params " + trade.getDate() + " , " +  trade.getTicker()  );
//
//                prepGetTrade.setString(1, trade.getDate());
//                prepGetTrade.setString(2, trade.getTicker());
//                ResultSet resultSet = prepGetTrade.executeQuery();

                String findSql = "select * from  "+ db +"."+ dailyTradeTable +" t " +
                        "where t.date = ? and t.ticker = ? ";
                 Object[] objArray = {trade.getDate(), trade.getTicker()};
                SqlRowSet rowSet = this.queryForRowSet(findSql, objArray);
                ResultSetWrappingSqlRowSet resultSetWrappingSqlRowSet = (ResultSetWrappingSqlRowSet) rowSet;
                ResultSet resultSet = resultSetWrappingSqlRowSet.getResultSet();


                while (resultSet.next()) {

                    previousTradeCount = resultSet.getLong("TRADES");
                    previousVol = resultSet.getLong("PRE_VOL");
                }

                log.info( trade.getTicker() + " PREVIOUS TRADE COUNT : " + previousTradeCount + " PREVIOUS VOL : " + previousVol);
                returnTrade.setPreviousTradeCount(previousTradeCount);
               // prepGetTrade.close();

                if (0L == previousTradeCount) {

                    log.debug("NO TRADE FOUND. INSERTING "+ trade.getTicker());

                    /* Here the trade will be inserted with the recorded volume. */
                    returnTrade.setLastVol(newVol);

                    String tableTrades = "DAILY_TRADES";

//                    Statement insertTrade = con.createStatement();
//                    int val = insertTrade.executeUpdate(trade.getSqlString(db,tableTrades));
//                    log.debug("Trade Inserted : " + val);


                    String insetQuery = "INSERT INTO `upuldi_CSEDAILYDATA`.`" + tableTrades + "` (`id`, `DATE`, `TICKER`, `PRE_VOL`, `TRADES`) " +
                            "VALUES ( ? , ?, ?, ?, ?)";

                    Object[] insertParamArray = {trade.getId(), trade.getDate(), trade.getTicker(), trade.getLastVol(), trade.getTradeCount()};
                    this.update(insetQuery, insertParamArray);


                  //  insertTrade.close();

                } else if (newTradeCount > previousTradeCount && 0L < previousTradeCount) {

                    log.debug("NEW TRADES FOUND FOR "+  trade.getTicker()  +"  UPDATING TRADE COUNT : " + trade.getTradeCount());

                    /* Here the volume needed to be calculated. */
                    returnTrade.setLastVol(newVol - previousVol);


                    String updateSql = "update " + db + "."+ dailyTradeTable+
                            " set TRADES = ? , PRE_VOL =  ? " +
                            " where date = ? and ticker = ? ;";

//                    PreparedStatement prepUpdateTrade = con.prepareStatement(updateSql);
//                    prepUpdateTrade.setLong(1, trade.getTradeCount());
//                    prepUpdateTrade.setLong(2, newVol);
//                    prepUpdateTrade.setString(3, trade.getDate());
//                    prepUpdateTrade.setString(4, trade.getTicker());
//                    prepUpdateTrade.executeUpdate();
                   // prepUpdateTrade.close();

                    //returnTrade.setPreviousTradeCount(trade.getTradeCount());


                    Object[] insertParamArray = {trade.getTradeCount(), newVol, trade.getDate(), trade.getTicker()};
                    this.update(updateSql, insertParamArray);

                }


            } catch (SQLException s) {
                log.error("SQL statement is not executed for  " + dailyTradeTable + " !");
                s.printStackTrace();
            } finally {
//                if (con.isClosed() == false) {
//                    con.close();
//                }
            }
        } catch (Exception e) {
            log.error(" ERROR OCCURED.... " + e.getMessage());
            e.printStackTrace();
        }

        return returnTrade;
    }

}
