package com.dao;

import com.domain.DailyMarketSummery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketSummeryDAOHibernate extends HibernateDaoSupport implements MarketSummeryDAO {


    public void saveMarketSummery(DailyMarketSummery scrapedMarketSummery) {
        getHibernateTemplate().save(scrapedMarketSummery);
    }
}
