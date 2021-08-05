package com.process;

import com.domain.Stock;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/11/11
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistDBEngine {

    private static Logger log = Logger.getLogger(PersistDBEngine.class);

    static Properties properties = new Properties();
    static Map<String, Boolean> vwaPriceSkipMap = null;
    static Map<String, Boolean> lastTradedPriceSkipMap = null;
    static Map<String, Boolean> lastTradedDateSkipMap = null;
    static Map<String, Boolean> highSkipMap = null;
    static Map<String, Boolean> lowSkipMap = null;
    static Map<String, Boolean> foreginHoldingSkipMap = null;
    static Map<String, Boolean> issuedQuentitySkipMap = null;
    static Map<String, Boolean> turnOverSkipMap = null;
    static Map<String, Boolean> indexedMarketCapSkipMap = null;
    static Map<String, Boolean> qtyInCDSSkipMap = null;


    private static void init() throws IOException {

        properties.load(new FileInputStream("/home/udoluweera/Experiments/CSE_DAILY_PROCESS/src/process.properties"));
        vwaPriceSkipMap = populateSkipListFromProperty(properties.getProperty("VwaPriceSkipList"));
        lastTradedPriceSkipMap = populateSkipListFromProperty(properties.getProperty("LastTradedPriceSkipList"));
        lastTradedDateSkipMap = populateSkipListFromProperty(properties.getProperty("LastTradedDateSkipList"));
        highSkipMap = populateSkipListFromProperty(properties.getProperty("HighSkipList"));
        lowSkipMap = populateSkipListFromProperty(properties.getProperty("LowSkipList"));
        foreginHoldingSkipMap = populateSkipListFromProperty(properties.getProperty("ForeginHoldingSkipList"));
        issuedQuentitySkipMap = populateSkipListFromProperty(properties.getProperty("IssuedQuentitySkipList"));
        turnOverSkipMap = populateSkipListFromProperty(properties.getProperty("TurnOVerSkipList"));
        indexedMarketCapSkipMap = populateSkipListFromProperty(properties.getProperty("IndexedMarketCapSkipList"));
        qtyInCDSSkipMap = populateSkipListFromProperty(properties.getProperty("QtyInCDSSkipList"));

    }

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {

        log.debug("Starting Programme..................................");

        init();

        String processFileName = properties.getProperty("process.step2.file");
        log.debug("USing File : " + processFileName);

        Scanner scanner = new Scanner(new FileReader(processFileName));

        List<Stock> processedStockList = new ArrayList<Stock>();

        int count = 0;

        while (scanner.hasNext()) {

            String currentLine = scanner.nextLine();
            log.debug(currentLine);


            String[] lineSections = currentLine.split("[\t]");

            if (null != lineSections && lineSections.length < 3) {

                /* Not a complete line so the amemndment to the line above. */

                Stock lastStock = processedStockList.get(count - 1);

                String amendTickerString = lastStock.getTickerString() + lineSections[1];

                log.debug("************* AMEMNDMENT ************** From  : " + lastStock.getTickerString() + " To : " + amendTickerString);

                lastStock.setTickerString(amendTickerString);
                continue;
            }

            boolean setVWaPrice = false;
            boolean setLastTradedPriceDate = false;
            boolean setHigh = false;
            boolean setLow = false;
            boolean setForeginHolding = false;
            boolean setIssuedQuantity = false;
            boolean setTurnover = false;
            boolean setIndexedMarketCap = false;
            boolean setQtyInCDS = false;


            Stock stock = new Stock();

            for (int i =0;i< lineSections.length;i++) {


                String currentValue = lineSections[i];

                /* Process Ticker List. */
                if (i==0) {
                    stock.setTickerString(currentValue);
                    continue;
                }
                /* Set VWA Price. */
                if (null == vwaPriceSkipMap.get(stock.getTickerString()) && !setVWaPrice) {
                    setStockAttributeValue(currentValue, stock, "vwaPrice");
                    setVWaPrice = true;
                    continue;
                }
                /* Set Last Traded Price and Date */
                if ((null == lastTradedPriceSkipMap.get(stock.getTickerString()) ||
                        null == lastTradedDateSkipMap.get(stock.getTickerString()) ) && !setLastTradedPriceDate )  {


                    String lastTradedPriceAndDate = currentValue;
                    String lastTradedPrice = null;
                    String lastTradedDate = null;

                    if (null == lastTradedPriceSkipMap.get(stock.getTickerString()) && null == lastTradedDateSkipMap.get(stock.getTickerString())) {

                        lastTradedPrice = lastTradedPriceAndDate.substring(0, lastTradedPriceAndDate.length() - 8);
                        lastTradedDate = lastTradedPriceAndDate.substring((lastTradedPriceAndDate.length() - 8), lastTradedPriceAndDate.length());

                    } else if (null == lastTradedPriceSkipMap.get(stock.getTickerString()) && null != lastTradedDateSkipMap.get(stock.getTickerString())) {

                        lastTradedPrice = lastTradedPriceAndDate;

                    } else if (null != lastTradedPriceSkipMap.get(stock.getTickerString()) && null == lastTradedDateSkipMap.get(stock.getTickerString())) {

                        lastTradedDate = lastTradedPriceAndDate;
                    }

                    log.debug(" Last Traded Price : " + lastTradedPrice);
                    log.debug(" Last Traded Date : " + lastTradedDate);

                    stock.setLastTradedPRice(lastTradedPrice);
                    stock.setLastTradedDate(lastTradedDate);

                    setLastTradedPriceDate = true;
                    continue;
                }
                /* Set high value. */
                if (null == highSkipMap.get(stock.getTickerString()) && !setHigh) {
                    setStockAttributeValue(currentValue, stock, "high");
                    setHigh = true;
                    continue;
                }
                /* Set low value */
                if (null == lowSkipMap.get(stock.getTickerString()) && !setLow) {
                    setStockAttributeValue(currentValue, stock, "low");
                    setLow = true;
                    continue;
                }
                /* Set forign holding value. */
                if (null == foreginHoldingSkipMap.get(stock.getTickerString()) && !setForeginHolding) {
                    setStockAttributeValue(currentValue, stock, "foreginHolding");
                    setForeginHolding = true;
                    continue;
                }
                /* Set issued qty value. */
                if (null == issuedQuentitySkipMap.get(stock.getTickerString()) && ! setIssuedQuantity) {
                    setStockAttributeValue(currentValue, stock, "issuedQuantity");
                    setIssuedQuantity = true;
                    continue;
                }
                /* set turnover value. */
                if (null == turnOverSkipMap.get(stock.getTickerString()) && !setTurnover ) {
                    setStockAttributeValue(currentValue, stock, "turnover");
                    setTurnover = true;
                    continue;
                }
                /* set indexted market cap value. */
                if (null == indexedMarketCapSkipMap.get(stock.getTickerString()) && !setIndexedMarketCap) {
                    setStockAttributeValue(currentValue, stock, "indexedMarketCap");
                    setIndexedMarketCap = true;
                    continue;
                }
                /* set qty in cds value. */
                if (null == qtyInCDSSkipMap.get(stock.getTickerString()) && !setQtyInCDS) {
                    setStockAttributeValue(currentValue, stock, "qtyInCDS");
                    setQtyInCDS = true;
                    continue;
                }
            }

            processedStockList.add(stock);
            log.debug(stock.toString());
            count = count + 1;

        }

        printList(processedStockList);

    }

    private static void setStockAttributeValue(String valueString, Stock stock, String attributeName) throws NoSuchFieldException, IllegalAccessException {

            Field checkingField = stock.getClass().getDeclaredField(attributeName);
            checkingField.setAccessible(true);
            checkingField.set(stock, valueString);
    }

    private static Map<String, Boolean> populateSkipListFromProperty(String vwaPriceSkipList) {

        Map<String, Boolean> skipIndexMap = new HashMap<String, Boolean>();

        for (String skipStr : vwaPriceSkipList.split("[|]")) {
            skipIndexMap.put(skipStr, Boolean.TRUE);
        }
        return skipIndexMap;
    }

    private static void printList(List<Stock> processedStockList) {

        for (Stock stock : processedStockList) {
            System.out.println(stock.toString());
        }
    }


}
