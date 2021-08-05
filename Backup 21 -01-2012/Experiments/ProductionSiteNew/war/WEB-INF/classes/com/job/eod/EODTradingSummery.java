package com.job.eod;

import com.dao.MarketSummeryDAO;
import com.domain.DailyMarketSummery;
import com.domain.MarketStatus;
import com.manager.DailyMarketSummeryManager;
import com.util.CSEData;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODTradingSummery {

    private static Logger log = Logger.getLogger(EODTradingSummery.class);

    private DailyMarketSummeryManager dailyMarketSummeryManager;
    private MarketSummeryDAO marketSummeryDAO;

    public void setMarketSummeryDAO(MarketSummeryDAO marketSummeryDAO) {
        this.marketSummeryDAO = marketSummeryDAO;
    }

    public void setDailyMarketSummeryManager(DailyMarketSummeryManager dailyMarketSummeryManager) {
        this.dailyMarketSummeryManager = dailyMarketSummeryManager;
    }

    public String processEODTradingSummery() throws ParseException, IOException {

        log.debug("Process EOD Trading Summery...");
        String returnString = "SUCCESS";

        MarketStatus marketStatus = CSEData.getMarketStatusData();
        String date = marketStatus.getDate();
        Date marketDateObj = marketStatus.getDateObj();
        Date nowDate = new Date();

        if (nowDate.getDate() == marketDateObj.getDate()) {

            DailyMarketSummery dailyMarketSummery = dailyMarketSummeryManager.getDailyTradingSummeryForTheDay();
            log.debug(" RESULTS : " + dailyMarketSummery.getCseAllShareIndex());
            marketSummeryDAO.saveMarketSummery(dailyMarketSummery);
        }else {
            log.debug("NOT A MARKET DAY");
            returnString = "NOT A MARKET DAY";
        }


        return returnString;
    }

}
