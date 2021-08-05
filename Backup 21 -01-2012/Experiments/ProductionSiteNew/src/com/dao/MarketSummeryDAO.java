package com.dao;

import com.domain.DailyMarketSummery;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MarketSummeryDAO {

    void saveMarketSummery(DailyMarketSummery scrapedMarketSummery);

}
