package com.test;

import com.domain.Stock;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/26/11
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Engine {


    private static Logger log = Logger.getLogger(Engine.class);

    static Map<Integer, Boolean> ignoreCountMap = new HashMap<Integer, Boolean>();
    static Map<String, Integer> processedStockMap = new HashMap<String, Integer>();


    public static void main(String[] args) throws IOException {

        log.debug("Starting.....");

        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("file:///home/udoluweera/Experiments/CSE_DAILY_PROCESS/input/page55.html");
        List<Stock> processedStockList = new ArrayList<Stock>();

        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();
        Map<Integer, HtmlDivision> diveMapForTthePage = new HashMap<Integer, HtmlDivision>();

        int divCount = 1;
        while (iteratorHtmlElement.hasNext()) {

            HtmlElement currentElement = iteratorHtmlElement.next();

            if (currentElement instanceof HtmlDivision) {

                HtmlDivision htmlDivision = (HtmlDivision) currentElement;
                diveMapForTthePage.put(divCount, htmlDivision);
                divCount = divCount + 1;
            }
        }

        /* Processing DIV Tags */

        /* Set Ticker Names and Board. */
        List<HtmlDivision> ticTitleList = new ArrayList<HtmlDivision>();
        ticTitleList.add(diveMapForTthePage.get(16));
        ticTitleList.add(diveMapForTthePage.get(88));

        setTickerNamesAndBoard(ticTitleList, processedStockList);

        /* Set VWA Price. */
        List<HtmlDivision> vwaPriceList = new ArrayList<HtmlDivision>();
        vwaPriceList.add(diveMapForTthePage.get(14));
        vwaPriceList.add(diveMapForTthePage.get(15));
        vwaPriceList.add(diveMapForTthePage.get(19));
        vwaPriceList.add(diveMapForTthePage.get(17));
        vwaPriceList.add(diveMapForTthePage.get(18));
        vwaPriceList.add(diveMapForTthePage.get(20));
        vwaPriceList.add(diveMapForTthePage.get(21));
        vwaPriceList.add(diveMapForTthePage.get(22));
        vwaPriceList.add(diveMapForTthePage.get(23));
        vwaPriceList.add(diveMapForTthePage.get(89));

        Map<Integer, Boolean> vwaPriceSkipMap = new HashMap<Integer, Boolean>();
        String[] vwaPriceSkipList = {"CAPITAL LEASING (+)","MERCANTILE INV","SENKADAGALA"};
        populateMapWithField(vwaPriceSkipList,vwaPriceSkipMap);
        setPrices(vwaPriceList, processedStockList, "vwaPrice", vwaPriceSkipMap);

        /* Set Last Traded Price. */
        List<HtmlDivision> lastTradedPriceList = new ArrayList<HtmlDivision>();
        lastTradedPriceList.add(diveMapForTthePage.get(24));
        lastTradedPriceList.add(diveMapForTthePage.get(25));
        lastTradedPriceList.add(diveMapForTthePage.get(26));
        lastTradedPriceList.add(diveMapForTthePage.get(27));
        lastTradedPriceList.add(diveMapForTthePage.get(28));
        lastTradedPriceList.add(diveMapForTthePage.get(79));
        lastTradedPriceList.add(diveMapForTthePage.get(90));

        Map<Integer, Boolean> lastTradedPriceSkipMap = new HashMap<Integer, Boolean>();
        setPrices(lastTradedPriceList, processedStockList, "lastTradedPRice", lastTradedPriceSkipMap);

        /* Set Last Traded Date. */
        List<HtmlDivision> lastTradedDateList = new ArrayList<HtmlDivision>();
        lastTradedDateList.add(diveMapForTthePage.get(29));
        lastTradedDateList.add(diveMapForTthePage.get(30));
        lastTradedDateList.add(diveMapForTthePage.get(31));
        lastTradedDateList.add(diveMapForTthePage.get(32));
        lastTradedDateList.add(diveMapForTthePage.get(33));
        lastTradedDateList.add(diveMapForTthePage.get(34));
        lastTradedDateList.add(diveMapForTthePage.get(35));
        lastTradedDateList.add(diveMapForTthePage.get(36));
        lastTradedDateList.add(diveMapForTthePage.get(80));
        lastTradedDateList.add(diveMapForTthePage.get(91));

        Map<Integer, Boolean> lastTradedDateSkipMap = new HashMap<Integer, Boolean>();
        String[] lastTradeddateSkipList = {"CAPITAL LEASING (+)","MERCANTILE INV","SENKADAGALA"};
        populateMapWithField(lastTradeddateSkipList,lastTradedDateSkipMap);
        setPrices(lastTradedDateList, processedStockList, "lastTradedDate", lastTradedDateSkipMap);

        /* Set High. */
        List<HtmlDivision> highList = new ArrayList<HtmlDivision>();
        highList.add(diveMapForTthePage.get(37));
        highList.add(diveMapForTthePage.get(38));
        highList.add(diveMapForTthePage.get(45));
        highList.add(diveMapForTthePage.get(49));
        highList.add(diveMapForTthePage.get(50));
        highList.add(diveMapForTthePage.get(81));
        highList.add(diveMapForTthePage.get(92));

        Map<Integer, Boolean> highSkipMap = new HashMap<Integer, Boolean>();
        setPrices(highList, processedStockList, "high", highSkipMap);

        /* Set Low. */
        List<HtmlDivision> lowList = new ArrayList<HtmlDivision>();
        lowList.add(diveMapForTthePage.get(39));
        lowList.add(diveMapForTthePage.get(40));
        lowList.add(diveMapForTthePage.get(46));
        lowList.add(diveMapForTthePage.get(51));
        lowList.add(diveMapForTthePage.get(52));
        lowList.add(diveMapForTthePage.get(82));
        lowList.add(diveMapForTthePage.get(93));

        Map<Integer, Boolean> lowSkipMap = new HashMap<Integer, Boolean>();
        setPrices(lowList, processedStockList, "low", lowSkipMap);

        /* Set Foregin Holding. */
        List<HtmlDivision> foreginHoldingList = new ArrayList<HtmlDivision>();
        foreginHoldingList.add(diveMapForTthePage.get(41));
        foreginHoldingList.add(diveMapForTthePage.get(42));
        foreginHoldingList.add(diveMapForTthePage.get(47));
        foreginHoldingList.add(diveMapForTthePage.get(53));
        foreginHoldingList.add(diveMapForTthePage.get(54));
        foreginHoldingList.add(diveMapForTthePage.get(55));
        foreginHoldingList.add(diveMapForTthePage.get(56));
        foreginHoldingList.add(diveMapForTthePage.get(83));
        foreginHoldingList.add(diveMapForTthePage.get(94));

        Map<Integer, Boolean> foreginHoldingSkipMap = new HashMap<Integer, Boolean>();
        String[] skipArray = {"THE FINANCE CO.[X.0000]", "PEOPLE'S L FIN[W.0020]"};
        populateMapWithField(skipArray, foreginHoldingSkipMap);

        setPrices(foreginHoldingList, processedStockList, "foreginHolding", foreginHoldingSkipMap);

        /* Set Issued Quentity List. */
        List<HtmlDivision> issuedQuentityList = new ArrayList<HtmlDivision>();
        issuedQuentityList.add(diveMapForTthePage.get(43));
        issuedQuentityList.add(diveMapForTthePage.get(44));
        issuedQuentityList.add(diveMapForTthePage.get(48));
        issuedQuentityList.add(diveMapForTthePage.get(57));
        issuedQuentityList.add(diveMapForTthePage.get(58));
        issuedQuentityList.add(diveMapForTthePage.get(84));
        issuedQuentityList.add(diveMapForTthePage.get(95));

        Map<Integer, Boolean> issuedQuentitySkipMap = new HashMap<Integer, Boolean>();
        setPrices(issuedQuentityList, processedStockList, "issuedQuantity", issuedQuentitySkipMap);

        /* Set Turnover List. */
        List<HtmlDivision> turnOverList = new ArrayList<HtmlDivision>();
        turnOverList.add(diveMapForTthePage.get(59));
        turnOverList.add(diveMapForTthePage.get(62));
        turnOverList.add(diveMapForTthePage.get(63));
        turnOverList.add(diveMapForTthePage.get(64));
        turnOverList.add(diveMapForTthePage.get(65));
        turnOverList.add(diveMapForTthePage.get(85));
        turnOverList.add(diveMapForTthePage.get(96));

        Map<Integer, Boolean> turnOverSkipMap = new HashMap<Integer, Boolean>();
        setPrices(turnOverList, processedStockList, "turnover", turnOverSkipMap);

        /* Set Indexed Market Cap List. */
        List<HtmlDivision> indexedMarketCapList = new ArrayList<HtmlDivision>();
        indexedMarketCapList.add(diveMapForTthePage.get(60));
        indexedMarketCapList.add(diveMapForTthePage.get(61));
        indexedMarketCapList.add(diveMapForTthePage.get(66));
        indexedMarketCapList.add(diveMapForTthePage.get(67));
        indexedMarketCapList.add(diveMapForTthePage.get(68));
        indexedMarketCapList.add(diveMapForTthePage.get(69));
        indexedMarketCapList.add(diveMapForTthePage.get(70));
        indexedMarketCapList.add(diveMapForTthePage.get(71));
        indexedMarketCapList.add(diveMapForTthePage.get(72));
        indexedMarketCapList.add(diveMapForTthePage.get(73));
        indexedMarketCapList.add(diveMapForTthePage.get(86));
        indexedMarketCapList.add(diveMapForTthePage.get(97));

        Map<Integer, Boolean> indexedMarketCapSkipMap = new HashMap<Integer, Boolean>();

        String[] skipArrayMarketCap = {"CEYLINCO INS.[X.0000] (+)", "COMMERCIAL", "HNB[X.0000] (+)", "S M B LEASING[W.0015]", "S M B LEASING[X.0000] (+)",
                "S M B LEASING[W.0016]", "SEYLAN BANK[X.0000] (+)", "THE FINANCE CO.[X.0000]", "PEOPLE'S L FIN[W.0020]"};
        populateMapWithField(skipArrayMarketCap, indexedMarketCapSkipMap);


        setPrices(indexedMarketCapList, processedStockList, "indexedMarketCap", indexedMarketCapSkipMap);

        /* Set Qty in CDS List. */
        List<HtmlDivision> qtyInCDSList = new ArrayList<HtmlDivision>();
        qtyInCDSList.add(diveMapForTthePage.get(74));
        qtyInCDSList.add(diveMapForTthePage.get(75));
        qtyInCDSList.add(diveMapForTthePage.get(76));
        qtyInCDSList.add(diveMapForTthePage.get(77));
        qtyInCDSList.add(diveMapForTthePage.get(78));
        qtyInCDSList.add(diveMapForTthePage.get(87));
        qtyInCDSList.add(diveMapForTthePage.get(98));

        Map<Integer, Boolean> qtyInCDSSkipMap = new HashMap<Integer, Boolean>();

        setPrices(qtyInCDSList, processedStockList, "qtyInCDS", qtyInCDSSkipMap);

        printList(processedStockList);


    }

    private static void populateMapWithField(String[] skipArray, Map<Integer, Boolean> foreginHoldingSkipMap) {

        for (String tickerName : skipArray) {
            foreginHoldingSkipMap.put(processedStockMap.get(tickerName), Boolean.TRUE);
        }
    }


    private static void printList(List<Stock> processedStockList) {

        for (Stock stock : processedStockList) {
            log.debug(stock.toString());
        }
    }

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

    /**
     * This method is used to process divs to get the ticker names and the board.
     *
     * @param processedStockList
     */
    private static void setTickerNamesAndBoard(List<HtmlDivision> list, List<Stock> processedStockList) {

        List<String> sectorsList = getSectorsList();
        List<String> boardList = getBoardList();
        List<String> garbaseList = getGarbageList();
        List<String> ignoreList = getIgnoreList();


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
//                    stock.setSector(lastSectorFound);
//                    stock.setBoardType(lastBoardFound);
                    stock.setTickerString(trimLine);

                    processedStockList.add(stock);
                }

                count = count + 1;
            }
        }

        /* Finally set the process stock map. */

        Integer listIndex = 0;
        for (Stock stock : processedStockList) {
            processedStockMap.put(stock.getTickerString(), listIndex);
            listIndex = listIndex + 1;
        }
    }


    /**
     * This method is used to ignore lines with are not a valid entry nor "(+)"
     *
     * @return
     */
    private static List<String> getIgnoreList() {

        List<String> ignoreList = new ArrayList<String>();

        String[] garbageArray = {"BANK[X.0000] (+)"};

        for (String board : garbageArray) {
            ignoreList.add(board);
        }

        return ignoreList;


    }

    private static List<String> getGarbageList() {

        List<String> garbageList = new ArrayList<String>();

        String[] garbageArray = {"(+)"};

        for (String board : garbageArray) {
            garbageList.add(board);
        }

        return garbageList;
    }

    private static List<String> getBoardList() {

        List<String> boardList = new ArrayList<String>();

        String[] boardsArray = {"MAIN BOARD", "DIRI SAVI BOARD", "DEFAULT BOARD"};

        for (String board : boardsArray) {
            boardList.add(board);
        }

        return boardList;
    }

    private static List<String> getSectorsList() {

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