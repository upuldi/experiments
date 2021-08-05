package com.process;

import com.domain.Stock;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/11/11
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class RefineObjectsEngine {

    private static Logger log = Logger.getLogger(RefineObjectsEngine.class);

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

        String processFileName = properties.getProperty("process.step1.file");
        log.debug("USing File : " + processFileName);

        Scanner scanner = new Scanner(new FileReader(processFileName));

        List<Stock> processedStockList = new ArrayList<Stock>();

        int count = 0;

        while (scanner.hasNext()) {

            String currentLine = scanner.nextLine();
            log.debug(currentLine);


            String[] lineSections = currentLine.split("[\t]");

            if (null != lineSections && lineSections.length < 2) {

                /* Not a complete line so the amemndment to the line above. */

                Stock lastStock = processedStockList.get(count - 1);

                String amendTickerString = lastStock.getTickerString() + lineSections[0];

                log.debug("************* AMEMNDEMENT ************** From  : " + lastStock.getTickerString() + " To : " + amendTickerString);

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

            List<String> missingColumns = new ArrayList<String>();

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
                } else {
                    missingColumns.add("vwaPrice");
                }
                /* Set Last Traded Price and Date */
                if ((null == lastTradedPriceSkipMap.get(stock.getTickerString()) ||
                        null == lastTradedDateSkipMap.get(stock.getTickerString()) ) && !setLastTradedPriceDate )  {


                    String lastTradedPriceAndDate = currentValue;
                    String lastTradedPrice = null;
                    String lastTradedDate = null;

                    log.debug("lastTradedPriceAndDate : " + lastTradedPriceAndDate);

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

                    if (null == lastTradedPrice ) {
                        missingColumns.add("lastTradedPRice");
                    }

                    if (null == lastTradedDate ) {
                        missingColumns.add("lastTradedDate");
                    }

                    continue;

                } else {
                    missingColumns.add("lastTradedPRice");
                    missingColumns.add("lastTradedDate;");
                }
                /* Set high value. */
                if (null == highSkipMap.get(stock.getTickerString()) && !setHigh) {
                    setStockAttributeValue(currentValue, stock, "high");
                    setHigh = true;
                    continue;
                } else {
                    missingColumns.add("high");
                }
                /* Set low value */
                if (null == lowSkipMap.get(stock.getTickerString()) && !setLow) {
                    setStockAttributeValue(currentValue, stock, "low");
                    setLow = true;
                    continue;
                } else {
                    missingColumns.add("low");
                }
                /* Set forign holding value. */
                if (null == foreginHoldingSkipMap.get(stock.getTickerString()) && !setForeginHolding) {
                    setStockAttributeValue(currentValue, stock, "foreginHolding");
                    setForeginHolding = true;
                    continue;
                } else {
                    missingColumns.add("foreginHolding");
                }
                /* Set issued qty value. */
                if (null == issuedQuentitySkipMap.get(stock.getTickerString()) && ! setIssuedQuantity) {
                    setStockAttributeValue(currentValue, stock, "issuedQuantity");
                    setIssuedQuantity = true;
                    continue;
                } else {
                    missingColumns.add("issuedQuantity");
                }
                /* set turnover value. */
                if (null == turnOverSkipMap.get(stock.getTickerString()) && !setTurnover ) {
                    setStockAttributeValue(currentValue, stock, "turnover");
                    setTurnover = true;
                    continue;
                } else {
                    missingColumns.add("turnover");
                }
                /* set indexted market cap value. */
                if (null == indexedMarketCapSkipMap.get(stock.getTickerString()) && !setIndexedMarketCap) {
                    setStockAttributeValue(currentValue, stock, "indexedMarketCap");
                    setIndexedMarketCap = true;
                    continue;
                } else {
                    missingColumns.add("indexedMarketCap");
                }
                /* set qty in cds value. */
                if (null == qtyInCDSSkipMap.get(stock.getTickerString()) && !setQtyInCDS) {
                    setStockAttributeValue(currentValue, stock, "qtyInCDS");
                    setQtyInCDS = true;
                    continue;
                } else {
                    missingColumns.add("qtyInCDS");
                }
            }


            /* Validate Stock */
            validateStockForSettedValues(stock,missingColumns);

            processedStockList.add(stock);
            log.debug(stock.toString());
            count = count + 1;

        }

        printList(processedStockList);
        writeToFile(processedStockList);

    }

    private static void validateStockForSettedValues(Stock stock, List<String> missingColumns) throws NoSuchFieldException, IllegalAccessException {

        Map<String,Boolean> missingColumnMap = new HashMap<String,Boolean>();
        for (String missingCol : missingColumns) {
            missingColumnMap.put(missingCol,Boolean.TRUE);
        }

        for (Field checkingField : stock.getClass().getDeclaredFields()) {

            checkingField.setAccessible(true);

            if (null == checkingField.get(stock)) {

                if (null == missingColumnMap.get(checkingField.getName()) || Boolean.FALSE == missingColumnMap.get(checkingField.getName()) ) {

                    throw new RuntimeException("NULL VALUE FOUND FOR A COLUMN WHICH IS NOT EXPECTED TO HAVE NULL VALUE :: " +
                            stock.getTickerString() + " : " + checkingField.getName());
                }
            }

        }
    }

    private static void writeToFile(List<Stock> processedStockList) throws IOException {

        String processStep2File = properties.getProperty("process.step2.file");
        FileWriter fstream = new FileWriter(processStep2File);
        BufferedWriter out = new BufferedWriter(fstream);

        for (Stock stock : processedStockList) {
            out.write(stock.toString() + "\n");
        }

        out.close();
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
