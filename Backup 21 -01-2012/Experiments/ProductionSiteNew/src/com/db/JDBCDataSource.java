package com.db;

import com.framework.SpringContext;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/7/11
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class JDBCDataSource {

    private Logger log = Logger.getLogger(JDBCDataSource.class);

    protected String url = null;
    protected String db = null;
    protected String driver = null;
    protected String user = null;
    protected String passWd = null;


    protected Connection getJDBCConnection() throws ClassNotFoundException, SQLException, NamingException {

        log.debug("Creating a Connection..");

        Connection con = null;
        Properties properties = (Properties) SpringContext.getBean("appProperties");
        url = properties.getProperty("jdbc.connection.url");
        db = properties.getProperty("jdbc.db");
        driver = properties.getProperty("jdbc.driver");
        user = properties.getProperty("jdbc.user");
        passWd = properties.getProperty("jdbc.passwd");
        log.debug("Using URL : " + url + " with database " + db + " with driver " + driver);


        Class.forName(driver);
        con = DriverManager.getConnection(url + db, user, passWd);
        return con;



/*        InitialContext initialContext = new InitialContext();
        DataSource datasource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/mysql");

        if (datasource == null) {
            String message = "Could not find Mysql DataSource. We're about to have problems.";
            log.error("******* " + message);
            throw new RuntimeException(message);
        } else {
            log.debug("******** Connection Pool Initialized....");
        }

        return datasource.getConnection();*/

/*
        Properties properties = (Properties) SpringContext.getBean("appProperties");
        url = properties.getProperty("jdbc.connection.url");
        db = properties.getProperty("jdbc.db");
        driver = properties.getProperty("jdbc.driver");
        user = properties.getProperty("jdbc.user");
        passWd = properties.getProperty("jdbc.passwd");
        log.debug("Using URL : " + url + " with database " + db + " with driver " + driver);

        Class.forName(driver);
        BoneCPDataSource ds = new BoneCPDataSource();
        ds.setJdbcUrl(url+db);
        ds.setUsername(user);
        ds.setPassword(passWd);

        Connection connection = null;
        connection = ds.getConnection();

        if (connection == null) {
            log.error("DATABASE CONNECTION FAILURE..........");
            throw new RuntimeException("Couldnt Create the Connection to the Database !!! ");
        }

        return  connection;
*/
    }
}
