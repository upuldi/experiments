package com.dao;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/31/11
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RubberDao {

    public ResultSet getRubberValues(String type);
}
