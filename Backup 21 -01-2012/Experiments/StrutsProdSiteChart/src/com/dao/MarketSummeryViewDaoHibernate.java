package com.dao;

import com.domain.DailyMarketSummery;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/21/11
 * Time: 11:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketSummeryViewDaoHibernate extends HibernateDaoSupport
        implements MarketSummeryViewDao {


    public DailyMarketSummery getMarketSummeryView() {

        Query query = getSession().getNamedQuery("getCSEMarketSummeryLatest");
        DailyMarketSummery returnObj =  (DailyMarketSummery) query.uniqueResult();

        return returnObj;
    }
}
