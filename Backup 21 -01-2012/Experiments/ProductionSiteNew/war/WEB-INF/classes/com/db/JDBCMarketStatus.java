package com.db;

import com.domain.MarketStatus;
import com.framework.SpringContext;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/8/11
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class JDBCMarketStatus extends JdbcTemplate {

    private Logger log = Logger.getLogger(JDBCMarketStatus.class);
    private String tableName;


    public void saveMarketStatus(MarketStatus marketStatus) {

        log.debug("Saving market data for : " + marketStatus.getDate());

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        tableName = properties.getProperty("jdbc.marketstatus.table");
        String db = properties.getProperty("jdbc.db");

        Connection connection = null;

        try {

//            try {
//               // connection = getJDBCConnection();
//                Statement st = connection.createStatement();
//                int val = st.executeUpdate(marketStatus.getInsetSQL(this.db, tableName));
//                log.debug("One row inserted ......");
//            } catch (SQLException s) {
//                log.debug("SQL statement is not executed for  " + tableName + " !");
//                log.error(s.getMessage());
//
//            } finally {
//                if (connection.isClosed() == false) {
//                    connection.close();
//                }
//            }

            String insertQuery = "INSERT INTO `"+ db +"`.`"+ tableName +"` (`id`, `date`, `status`) VALUES (?, ? , ?)";
            this.update(insertQuery, new Object[] {marketStatus.getId() ,marketStatus.getDate(), marketStatus.getStatus()});


        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

}
