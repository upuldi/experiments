package com.dao;

import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/1/11
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MarketSummeryDao {

    ResultSet getMarketSummeryValues(String type);

    ResultSet getMarketSummerySectionValues(String type);

    ResultSet getContributorSummeryValues(String type);

}
