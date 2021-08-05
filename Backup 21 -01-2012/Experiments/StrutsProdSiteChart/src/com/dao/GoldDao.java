package com.dao;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 1/19/12
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GoldDao {

    ResultSet getGoldValues(String type);
}
