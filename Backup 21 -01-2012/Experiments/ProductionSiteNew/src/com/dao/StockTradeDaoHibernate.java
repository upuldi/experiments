package com.dao;

import com.domain.StockTrade;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/18/11
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockTradeDaoHibernate extends HibernateDaoSupport implements StockTradeDao {


    public void addStockTrades(List<StockTrade> stockTradesListForSelectedCounter) {
        getHibernateTemplate().saveOrUpdateAll(stockTradesListForSelectedCounter);
    }

    public void addSingleStockTrade(StockTrade stockTrade) {
        getHibernateTemplate().save(stockTrade);
    }

}
