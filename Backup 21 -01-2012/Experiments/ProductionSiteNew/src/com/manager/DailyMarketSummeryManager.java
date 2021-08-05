package com.manager;

import com.domain.DailyMarketSummery;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DailyMarketSummeryManager {
        DailyMarketSummery getDailyTradingSummeryForTheDay() throws IOException;
}
