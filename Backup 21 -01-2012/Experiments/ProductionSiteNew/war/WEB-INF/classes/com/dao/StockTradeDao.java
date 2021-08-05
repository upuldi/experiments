package com.dao;

import com.domain.StockTrade;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/18/11
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StockTradeDao {

    void addStockTrades(List<StockTrade> stockTradesListForSelectedCounter);

    void addSingleStockTrade(StockTrade stockTrade);
}
