package com.job.realtime;

import au.com.bytecode.opencsv.CSVReader;
import com.db.JDBCMarketStatus;
import com.db.JDBCRTEData;
import com.db.JDBCTRADEData;
import com.domain.MarketStatus;
import com.domain.RTData;
import com.domain.RTIndexValues;
import com.domain.Trade;
import com.framework.SpringContext;
import com.util.CSEData;
import com.util.CSERtIndexData;
import com.util.CSE_CONSTANTS;
import com.util.UniversalTime;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/7/11
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class RealTimeMarketData {

    private static final String RT_DATA_TXT_FILE = "RT-DATA.txt";
    private JDBCMarketStatus jdbcMarketStatus;
    private JDBCRTEData jdbcrteData;


    private DateTime currentTime;

    private JDBCTRADEData jdbctradeData;

    public void setJdbcrteData(JDBCRTEData jdbcrteData) {
        this.jdbcrteData = jdbcrteData;
    }

    public void setJdbctradeData(JDBCTRADEData jdbctradeData) {
        this.jdbctradeData = jdbctradeData;
    }

    public void setJdbcMarketStatus(JDBCMarketStatus jdbcMarketStatus) {
        this.jdbcMarketStatus = jdbcMarketStatus;
    }

    private static Logger log = Logger.getLogger(RealTimeMarketData.class);


    public String processMarketData() throws ParseException, IOException {

        MarketStatus marketStatus = CSEData.getMarketStatusData();
        currentTime = UniversalTime.getTime();

        log.info(" PROCESSING REAL-TIME MARKET DATA.... " + currentTime);

        String returnString = "SUCCESS";

            log.debug(" Market Status : " + marketStatus.getStatus());
            log.debug(" Market Date : " + marketStatus.getDate());

            if (CSE_CONSTANTS.MARKET_OPEN.equalsIgnoreCase(marketStatus.getStatus())) {

                log.debug("CSE MARKET DATE...");

                DateTime startTimeDate = currentTime.withTime(9,30,0,0);
                DateTime endTimeDate = currentTime.withTime(14,30,0,0);

                log.debug("CONSIDERING TIME : " + currentTime);
                log.debug("START TIME : " + startTimeDate);
                log.debug("END TIME : " + endTimeDate);

                if (currentTime.isAfter(startTimeDate.getMillis()) && currentTime.isBefore(endTimeDate.getMillis())) {

                    log.info("MARKET IS OPEN....");

                    Properties properties = (Properties) SpringContext.getBean("appProperties");
                    String cseStockDailCSVUrl = properties.getProperty("cse.stock.daily.csv.url");

                    URL cseStockOverview = new URL(cseStockDailCSVUrl);
                    URLConnection urlConnection = cseStockOverview.openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

/*                    File convertedFile = new File(RT_DATA_TXT_FILE);
                    Writer output = new BufferedWriter(new FileWriter(convertedFile));*/

                    String DATE_FORMAT = "yyyyMMdd";
                    //SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

                    String dateString = currentTime.toString(DATE_FORMAT);

                    String timeFormat = "HH:mm:ss";
                   // SimpleDateFormat sdfTimeFormat = new SimpleDateFormat(timeFormat);

                    String timeString = currentTime.toString(timeFormat);
                    String timeStringWOSecounds = timeString.substring(0, 5) + ":00";

                    log.debug("TIME :  " + timeString + " WO SEC : " + timeStringWOSecounds);

                    /* Save RT Index Values. */
                    RTIndexValues rtIndexData = CSERtIndexData.getRTIndexData();
                    rtIndexData.setDate(dateString);
                    rtIndexData.setTime(timeStringWOSecounds);

                    jdbcrteData.saveIndexData(rtIndexData);

                    CSVReader reader = new CSVReader(bufferedReader);
                    String[] nextLine;

                    while ((nextLine = reader.readNext()) != null) {

                        NumberFormat nf = NumberFormat.getInstance();

                        Long tradeCountNow = nf.parse(nextLine[3]).longValue();
                        String ticker = nextLine[1];
                        long recordVolume = nf.parse(nextLine[2]).longValue();

                        /* Check whether to enter the record or not .*/
                        Trade trade = new Trade();
                        trade.setDate(dateString);
                        trade.setTicker(ticker);
                        trade.setLastVol(recordVolume);
                        trade.setTradeCount(tradeCountNow);

                        log.info("FOUND RECORD  TICKER : " + ticker + " WITH VOL : " + recordVolume + " TRADE COUNT : " + tradeCountNow ) ;

                        Trade returnedTrade = jdbctradeData.getTradeCount(trade);

                        if (tradeCountNow > returnedTrade.getPreviousTradeCount() && returnedTrade.getPreviousTradeCount() != tradeCountNow) {

                            /* A Record should be entered. */

                            RTData rtData = new RTData();
                            rtData.setTicker(ticker);
                            rtData.setDate(dateString);
                            rtData.setTime(timeStringWOSecounds);
                            rtData.setOpen(nextLine[5]);
                            rtData.setHigh(nextLine[6]);
                            rtData.setLow(nextLine[7]);
                            rtData.setClose(nextLine[8]);
                            rtData.setVol(returnedTrade.getLastVol());

                            /*Save RT Data. */

                            jdbcrteData.saveCSEData(rtData);

                            log.info("INSERTED NEW TRADE : " + rtData.getTicker() + " VOL : " + returnedTrade.getLastVol());

                        } else {

                            log.info("NO NEW TRADES HAS HAPPNED TO : " + nextLine[1]);
                        }
                    }

                } else {

                    log.info("MARKET CLOSED FOR TRADING...");
                }
            } else {
                log.info("NOT A CSE MARKET DATE");
                returnString = "NOT A CSE MARKET DATE";
            }

           return returnString;
    }

}
