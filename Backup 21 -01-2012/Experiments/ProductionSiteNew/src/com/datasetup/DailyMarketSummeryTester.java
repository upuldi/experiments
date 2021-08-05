package com.datasetup;

import com.manager.DailyMarketSummeryManager;
import com.manager.DailyMarketSummeryManagerImpl;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class DailyMarketSummeryTester {

    public static void main(String []args) throws IOException {

        DailyMarketSummeryManager dailyMarketSummeryManager = new DailyMarketSummeryManagerImpl();
        dailyMarketSummeryManager.getDailyTradingSummeryForTheDay();

    }


}
