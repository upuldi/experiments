package com.db;

import com.framework.SpringContext;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/18/11
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class DSTest extends JDBCDataSource {

    public void performTest() throws NamingException, ClassNotFoundException, SQLException {

        Connection con = getJDBCConnection();
        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String db = properties.getProperty("jdbc.db");
        String dailyTradeTable = properties.getProperty("jdbc.dailytrade.table");


        String findSql = "select * from  " + db + "." + dailyTradeTable + "";
        PreparedStatement prepGetTrade = con.prepareStatement(findSql);
        ResultSet resultSet = prepGetTrade.executeQuery();

        System.out.println(" RS" + resultSet.next());

        con.close();
    }

    public void performConnectionTest() {

        System.out.println(" PERFORMING TEST");

        for (int i = 0; i < 100; i++) {
            System.out.println(" <><><><><><><>><> CYCLE " + i + " <><><><><><><><><><><><><");

            try{
            Connection con = getJDBCConnection();
            Properties properties = (Properties) SpringContext.getBean("appProperties");
            String db = properties.getProperty("jdbc.db");
            String dailyTradeTable = properties.getProperty("jdbc.dailytrade.table");
            String findSql = "select count(*) as count from  " + db + "." + dailyTradeTable + "";
            PreparedStatement prepGetTrade = con.prepareStatement(findSql);
            ResultSet resultSet = prepGetTrade.executeQuery();

            while (resultSet.next()) {
                System.out.println(" COUNT IS : " + resultSet.getString("count"));
            }
            } catch(Exception e) {
                System.out.println("************ ERROR OCCURED" + e.getMessage());
                e.printStackTrace();
            }
            System.out.println(" <><><><><><><>><> END CYCLE " + i + " <><><><><><><><><><><><><");
        }

    }
}
