package com.dao;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/4/11
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EODChartDao {

    ResultSet getEODValues(String type);

    ResultSet getEODValuesForCandle(String type);

    ResultSet getEODForignHoldingValues(String stock);

    ResultSet getEODIssuedQtyValues(String stock);
}
