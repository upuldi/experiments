package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/26/11
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EODForignHoldingDao {
    ResultSet getEODForignHoldingValuesByLastTradedDate(String from, String to) throws SQLException, ParseException;
}
