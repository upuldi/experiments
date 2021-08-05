package com.dao;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/2/11
 * Time: 6:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RTChartDao {

    ResultSet getRTValues(String type, String date);

    ResultSet getRTindexValues(String type, String date);
}
