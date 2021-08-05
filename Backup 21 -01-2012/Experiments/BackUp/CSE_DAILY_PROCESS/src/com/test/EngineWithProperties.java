package com.test;

import com.domain.Stock;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/29/11
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class EngineWithProperties {

    static Map<Integer, HtmlDivision> diveMapForTthePage = new HashMap<Integer, HtmlDivision>();
    static Properties properties = new Properties();
    static Map<Integer, Boolean> ignoreCountMap = new HashMap<Integer, Boolean>();
    static Map<String, Integer> processedStocNameIndexkMap = new HashMap<String, Integer>();


    private static Logger log = Logger.getLogger(EngineWithProperties.class);

    private static void init() throws IOException {
        properties.load(new FileInputStream("/home/udoluweera/Experiments/CSE_DAILY_PROCESS/src/process.properties"));
    }

    public static void main(String[] args) throws IOException {

        log.debug("Starting..............");
        init();

        /* Populate The Div Map for the Page. */
        populateDivMapForThePage();

        /* Amend the DIV map for special DIVs */
        amendDivMapForSpecialEntries();

        //System.exit(0);

        /* Processing DIV Tags */
        List<Stock> processedStockList = new ArrayList<Stock>();

        /* Set Ticker Names and Board. */
        List<HtmlDivision> ticTitleList = getDivListFromProperty(properties.getProperty("TickerNamesAndBoardDIVNoS"));
        setTickerNamesAndBoard(ticTitleList, processedStockList);

        /* Set VWA Price. */
        List<HtmlDivision> vwaPriceList = getDivListFromProperty(properties.getProperty("VWAPriceDIVNoS"));
        Map<Integer, Boolean> vwaPriceSkipMap = populateSkipListFromProperty(properties.getProperty("VwaPriceSkipList"));
        setPrices(vwaPriceList, processedStockList, "vwaPrice", vwaPriceSkipMap);

        /* Set Last Traded Price. */
        List<HtmlDivision> lastTradedPriceList = getDivListFromProperty(properties.getProperty("LastTradedPriceDIVNoS"));
        Map<Integer, Boolean> lastTradedPriceSkipMap = populateSkipListFromProperty(properties.getProperty("LastTradedPriceSkipList"));
        setPrices(lastTradedPriceList, processedStockList, "lastTradedPRice", lastTradedPriceSkipMap);

        /* Set Last Traded Date. */
        List<HtmlDivision> lastTradedDateList = getDivListFromProperty(properties.getProperty("LastTradedDateDIVNoS"));
        Map<Integer, Boolean> lastTradedDateSkipMap = populateSkipListFromProperty(properties.getProperty("LastTradedDateSkipList"));
        setPrices(lastTradedDateList, processedStockList, "lastTradedDate", lastTradedDateSkipMap);

        /* Set High. */
        List<HtmlDivision> highList = getDivListFromProperty(properties.getProperty("HighDIVNoS"));
        Map<Integer, Boolean> highSkipMap = populateSkipListFromProperty(properties.getProperty("HighSkipList"));
        setPrices(highList, processedStockList, "high", highSkipMap);

        /* Set Low. */
        List<HtmlDivision> lowList = getDivListFromProperty(properties.getProperty("LowDIVNoS"));
        Map<Integer, Boolean> lowSkipMap = populateSkipListFromProperty(properties.getProperty("LowSkipList"));
        setPrices(lowList, processedStockList, "low", lowSkipMap);

        /* Set Foregin Holding. */
        List<HtmlDivision> foreginHoldingList = getDivListFromProperty(properties.getProperty("ForeginHoldingDIVNoS"));
        Map<Integer, Boolean> foreginHoldingSkipMap = populateSkipListFromProperty(properties.getProperty("ForeginHoldingSkipList"));
        setPrices(foreginHoldingList, processedStockList, "foreginHolding", foreginHoldingSkipMap);

        /* Set Issued Quentity List. */
        List<HtmlDivision> issuedQuentityList = getDivListFromProperty(properties.getProperty("IssuedQuentityDIVNoS"));
        Map<Integer, Boolean> issuedQuentitySkipMap = populateSkipListFromProperty(properties.getProperty("IssuedQuentitySkipList"));
        setPrices(issuedQuentityList, processedStockList, "issuedQuantity", issuedQuentitySkipMap);

        /* Set Turnover List. */
        List<HtmlDivision> turnOverList = getDivListFromProperty(properties.getProperty("TurnoverDIVNoS"));
        Map<Integer, Boolean> turnOverSkipMap = populateSkipListFromProperty(properties.getProperty("TurnOVerSkipList"));
        setPrices(turnOverList, processedStockList, "turnover", turnOverSkipMap);

        /* Set Indexed Market Cap List. */
        List<HtmlDivision> indexedMarketCapList = getDivListFromProperty(properties.getProperty("IndexedMarketCapDIVNoS"));
        Map<Integer, Boolean> indexedMarketCapSkipMap = populateSkipListFromProperty(properties.getProperty("IndexedMarketCapSkipList"));
        setPrices(indexedMarketCapList, processedStockList, "indexedMarketCap", indexedMarketCapSkipMap);

        /* Set Qty in CDS List. */
        List<HtmlDivision> qtyInCDSList = getDivListFromProperty(properties.getProperty("QtyInCDSNoS"));
        Map<Integer, Boolean> qtyInCDSSkipMap = populateSkipListFromProperty(properties.getProperty("QtyInCDSSkipList"));
        setPrices(qtyInCDSList, processedStockList, "qtyInCDS", qtyInCDSSkipMap);

        /* Finally Rename the Ticker Strings if required. */
        renameTickerNames(processedStockList);

        printList(processedStockList);

        /* Write to a file */
        writeOutPutToCVS(processedStockList);


    }

    private static void amendDivMapForSpecialEntries() {

        String specialDivPropEntry = properties.getProperty("SpecialDivSections");

        for (String entry : specialDivPropEntry.split("[$]")) {

            String[] indexSectoionArray = entry.split("[!]");
            String indexSet = indexSectoionArray[0];
            String sectionSet = indexSectoionArray[1];

            String[] indexSetEntries = indexSet.split("[,]");
            String[] sectionSetEntries = sectionSet.split("[|]");

            /* Index set entries should be equal to the section set entries */
            if ((indexSetEntries.length-1) != sectionSetEntries.length) {
                throw new RuntimeException("Configuration Error .... Index set does not match to section set...");
            }

            Integer baseDivIndex =  Integer.parseInt(indexSetEntries[0]);
            List<Integer> divIndexList = new ArrayList<Integer>();

            /* Add the other divs to a list */
            for (int i = 1 ; i < indexSetEntries.length ; i++) {
                divIndexList.add(Integer.parseInt(indexSetEntries[i]));
            }

            HtmlDivision baseHtmlDiv = diveMapForTthePage.get(baseDivIndex);
            String[] baseDivContentArray = baseHtmlDiv.getTextContent().split("[[\n]]");

            int divIndexListLength = divIndexList.size();
            int baseDivContentLength = baseDivContentArray.length;
            int modVal = baseDivContentLength % divIndexListLength;

            if (1 == modVal) {
                throw new RuntimeException("Can not proceeed .................");
            }

            for (int i =0 ; i < baseDivContentLength;i++) {

                int currentIndexFieldToAmend = i % divIndexListLength;
                HtmlDivision currentDiv = diveMapForTthePage.get(divIndexList.get(currentIndexFieldToAmend));

                String formingLineToAppend = baseDivContentArray[i] + "\n";
                String amendContent = currentDiv.getTextContent() + formingLineToAppend;
                currentDiv.setTextContent(amendContent);
            }


            /* Print Contents. */
            log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< DISPLAYING CONTENT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            for (Integer i : divIndexList) {

                log.debug("************************ DISPLAYING CONTENT OF " + i + " ***********************");
                log.debug("\n"+diveMapForTthePage.get(i).getTextContent());
                log.debug("***********************************************************");

            }

            log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        }
    }

    private static void writeOutPutToCVS(List<Stock> processedStockList) throws IOException {

        FileWriter fstream = new FileWriter("out.txt");
        BufferedWriter out = new BufferedWriter(fstream);

        for (Stock stock : processedStockList) {
             out.write(stock.toCVSString() + "\n");
        }

        out.close();
    }


    private static void printList(List<Stock> processedStockList) {

        for (Stock stock : processedStockList) {
              System.out.println(stock.toString());
        }
    }


    private static Map<Integer, Boolean> populateSkipListFromProperty(String vwaPriceSkipList) {

        Map<Integer, Boolean> skipIndexMap = new HashMap<Integer, Boolean>();

        for (String skipStr : vwaPriceSkipList.split("[|]")) {
            skipIndexMap.put(processedStocNameIndexkMap.get(skipStr), Boolean.TRUE);
        }
        return skipIndexMap;
    }


/*    private static void populateMapWithField(String[] skipArray, Map<Integer, Boolean> foreginHoldingSkipMap) {

        for (String tickerName : skipArray) {
            foreginHoldingSkipMap.put(processedStocNameIndexkMap.get(tickerName), Boolean.TRUE);
        }
    }*/

    private static void setPrices(List<HtmlDivision> vwaPriceList, List<Stock> processedStockList, String checkingFieldName,
                                  Map<Integer, Boolean> skipCountMap) {


        try {

            List<String> divLines = new ArrayList<String>();

            for (int i = 0; i < vwaPriceList.size(); i++) {
                String divTextContent = vwaPriceList.get(i).getTextContent();
                for (String line : divTextContent.split("[\n]")) {
                    divLines.add(line);
                }
            }

            int linesCount = 0;
            for (int i = 0; i < processedStockList.size(); i++) {

                /* Here we have to ignore the entries in the neglect list */
                if (null == skipCountMap.get(i)) {

                    Stock stock = processedStockList.get(i);
                    Field checkingField = stock.getClass().getDeclaredField(checkingFieldName);
                    checkingField.setAccessible(true);
                    checkingField.set(stock, divLines.get(linesCount));
                    linesCount = linesCount + 1;
                }
            }


        } catch (Exception e) {
            log.error("Exception Occured " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<HtmlDivision> getDivListFromProperty(String tickerNamesAndBoardDIVNoS) {

        List<HtmlDivision> htmlDivisions = new ArrayList<HtmlDivision>();

        for (String divNo : tickerNamesAndBoardDIVNoS.split("[,]")) {
            Integer divNoInt = Integer.parseInt(divNo);
            htmlDivisions.add(diveMapForTthePage.get(divNoInt));
        }

        return htmlDivisions;
    }

    private static void populateDivMapForThePage() throws IOException {

        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("file:///" + properties.getProperty("ProcessFileLocation"));

        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();

        int divCount = 1;
        while (iteratorHtmlElement.hasNext()) {

            HtmlElement currentElement = iteratorHtmlElement.next();

            if (currentElement instanceof HtmlDivision) {
                HtmlDivision htmlDivision = (HtmlDivision) currentElement;
                diveMapForTthePage.put(divCount, htmlDivision);
                divCount = divCount + 1;
            }
        }
    }


    /**
     * This method is used to process divs to get the ticker names and the board.
     *
     * @param processedStockList
     */
    private static void setTickerNamesAndBoard(List<HtmlDivision> list, List<Stock> processedStockList) {

        List<String> sectorsList = getTickerSectorsList();
        List<String> boardList = getTickerBoardList();
        List<String> garbaseList = getTickerGarbageList();
        List<String> ignoreList = getTickerIgnoreList();


        Integer count = new Integer(0);

        for (HtmlDivision htmlDivision : list) {

            String divTextContent = htmlDivision.getTextContent();
            log.debug("Content is : " + divTextContent);

            String lastSectorFound = "";
            String lastBoardFound = "";

            for (String line : divTextContent.split("[\n]")) {

                //   log.debug("Line is : " + line);
                String trimLine = line.trim();

                if (sectorsList.contains(trimLine) || boardList.contains(trimLine) || garbaseList.contains(trimLine) || ignoreList.contains(trimLine)) {

                    /* Set the last sector. */
                    if (sectorsList.contains(trimLine)) {
                        lastSectorFound = trimLine;
                    }

                    /* Set the last board. */
                    if (boardList.contains(trimLine)) {
                        lastBoardFound = trimLine;
                    }

                    /* Neglect the garbages. */
                    if (garbaseList.contains(trimLine)) {
                        continue;
                    }

                    /* Mark the ignore lines. */
                    if (ignoreList.contains(trimLine)) {
                        ignoreCountMap.put(count, true);
                        continue;
                    }

                } else {

                    log.debug(" Sector :  " + lastSectorFound + " Board : " + lastBoardFound + " Ticker " + trimLine);

                    Stock stock = new Stock();
                    stock.setSector(lastSectorFound);
                    stock.setBoardType(lastBoardFound);
                    stock.setTickerString(trimLine);

                    processedStockList.add(stock);
                }

                count = count + 1;
            }
        }


        /* Finally set the process stock map. */

        Integer listIndex = 0;
        for (Stock stock : processedStockList) {
            processedStocNameIndexkMap.put(stock.getTickerString(), listIndex);
            listIndex = listIndex + 1;
        }
    }

    private static void renameTickerNames(List<Stock> processedStockList) {


        String reNamePropertyStr = properties.getProperty("TickerRenameList");

        for (String pair : reNamePropertyStr.split("[,]")) {

            String[] pairArray = pair.split("[|]");
            String beforeName = pairArray[0];
            String afterName = pairArray[1];

            for (Stock stock : processedStockList) {

                if (stock.getTickerString().equals(beforeName.trim())) {

                    /* Now Change the Name. */
                    stock.setTickerString(afterName);
                }
            }
        }
    }

    /**
     * This method is used to ignore lines with are not a valid entry nor "(+)"
     *
     * @return
     */
    private static List<String> getTickerIgnoreList() {

        List<String> ignoreList = new ArrayList<String>();

        String tickerIgnorePropString = properties.getProperty("TickerIgnoreList");

        for (String ignoreStr : tickerIgnorePropString.split("[|]")) {
            ignoreList.add(ignoreStr);
        }
        return ignoreList;
    }

    private static List<String> getTickerGarbageList() {

        List<String> garbageList = new ArrayList<String>();

        String[] garbageArray = {"(+)"};

        for (String board : garbageArray) {
            garbageList.add(board);
        }

        return garbageList;
    }

    private static List<String> getTickerBoardList() {

        List<String> boardList = new ArrayList<String>();

        String[] boardsArray = {"MAIN BOARD", "DIRI SAVI BOARD", "DEFAULT BOARD"};

        for (String board : boardsArray) {
            boardList.add(board);
        }

        return boardList;
    }

    private static List<String> getTickerSectorsList() {

        List<String> sectorsList = new ArrayList<String>();

        String[] sectorsArray = {"BANKS FINANCE AND INSURANCE", "BEVERAGE FOOD AND TOBACCO", "CHEMICALS AND PHARMACEUTICALS",
                "CLOSED END FUNDS", "CONSTRUCTION AND ENGINEERING", "DIVERSIFIED HOLDINGS", "FOOTWEAR AND TEXTILES", "HEALTH CARE", "HOTELS AND TRAVELS",
                "INFORMATION TECHNOLOGY", "INVESTMENT TRUSTS", "LAND AND PROPERTY", "MANUFACTURING", "MOTORS", "OIL PALMS", "PLANTATIONS",
                "POWER AND ENERGY", "SERVICES", "STORES AND SUPPLIES", "TELECOMMUNICATIONS", "TRADING"};

        for (String sector : sectorsArray) {
            sectorsList.add(sector);
        }

        return sectorsList;
    }

}
