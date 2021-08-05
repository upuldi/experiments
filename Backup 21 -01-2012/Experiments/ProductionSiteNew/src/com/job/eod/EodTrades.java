package com.job.eod;

import com.dao.EODDataDao;
import com.dao.StockTradeDao;
import com.domain.EODData;
import com.domain.StockTrade;
import com.domain.TradeProcess;
import com.framework.SpringContext;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/16/11
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class EodTrades {

    private static Logger log = Logger.getLogger(EodTrades.class);

    EODDataDao eodDataDao;
    private StockTradeDao stockTradeDao;

    public void setStockTradeDao(StockTradeDao stockTradeDao) {
        this.stockTradeDao = stockTradeDao;
    }

    public void setEodDataDao(EODDataDao eodDataDao) {
        this.eodDataDao = eodDataDao;
    }

    public String processEodTrades() throws ParseException, IOException {


        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String tradeURL = properties.getProperty("jkcs.trade.url");
        NumberFormat nf = NumberFormat.getInstance();
        String processStatus = "";

        String dateFormatString = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString);

        /*Data extraction*/
        Date dateToday = Calendar.getInstance().getTime();

        String date = simpleDateFormat.format(dateToday);
        //date = "20110822";

        /* Create a folder for given date. TODO: Remove */
        String pathToFolder = properties.getProperty("trades.save.root.location");
//        String creatingFolder = pathToFolder + date + "/" ;
//        File dir = new File(pathToFolder);
//        boolean x = dir.mkdir();

       // log.debug("Folder Was Created..." + x);


        List<TradeProcess> tradeEntiesList = eodDataDao.getNextNotProcessedTradeEntriesForTheDay(date);

        /* If no Trade entry found it should be inserted carefully. */
        if (null == tradeEntiesList || tradeEntiesList.size() == 0) {

            /* NO trade entry found may be due to not a market day so need to check whether
            * whether the processed entried are available. */
            long processTradeCount = eodDataDao.getProcessedTradeCountForTheDay(date);

            if (processTradeCount > 0) {
                return " Trade Process Completed : " + processTradeCount;

            } else {
                /* Need to check whether the closed market date. */

                long eodTradedCountersCount = eodDataDao.getTradedCountersCountForTheDay(date);

                if (0 < eodTradedCountersCount) {

                    /* Need to insert records to be processed. */
                    List<EODData> tradedCountersList = eodDataDao.getTradedCountersForTheDay(date);
                    List<TradeProcess> tradeProcessListToAdd = new ArrayList<TradeProcess>();

                    for (EODData eodData : tradedCountersList) {

                        TradeProcess tradeProcess = new TradeProcess();
                        tradeProcess.setDate(date);
                        tradeProcess.setTicker(eodData.getTicker());
                        tradeProcess.setProcessed("NO");
                        tradeProcessListToAdd.add(tradeProcess);
                    }

                    eodDataDao.saveTradeProcessList(tradeProcessListToAdd);
                    return "Trade Processes Are Added For The Day";

                } else {

                    return "Not A Trading Date. No record found.";
                }
            }
        } else {


            String processedCountersStr = "";

            for (TradeProcess tradeProcess : tradeEntiesList) {

                List<StockTrade> stockTradesListForSelectedCounter = new ArrayList<StockTrade>();
                StringBuffer currentTradeUrl = new StringBuffer(tradeURL).append(tradeProcess.getTicker());
                String processInfo = "INITIATED";

                log.debug(" Current Trade URL : " + currentTradeUrl);
                final WebClient webClient = new WebClient();
                final HtmlPage page = webClient.getPage(currentTradeUrl.toString());

                Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();

                while (iteratorHtmlElement.hasNext()) {

                    HtmlElement currentElement = iteratorHtmlElement.next();

                    if (currentElement instanceof HtmlTable) {

                        log.debug(" *** HTML TABLE FOUND *** ");

                        HtmlTable htmlTable = (HtmlTable) currentElement;

                        int i = 0;
                        int totalSize = htmlTable.getRows().size();

                        for (HtmlTableRow htmlTableRow : htmlTable.getRows()) {

                            if (i > 3 && i != totalSize - 1) {

                                String time = htmlTableRow.getCell(0).getTextContent();
                                String trade = htmlTableRow.getCell(1).getTextContent();
                                String price = htmlTableRow.getCell(2).getTextContent();

                                log.debug(" TIME IS : " + time + " TRADE IS : " + trade + " PRICE IS : " + price);

                                StockTrade stockTrade = new StockTrade();
                                stockTrade.setDate(date);
                                stockTrade.setTicker(tradeProcess.getTicker());
                                stockTrade.setTime(time);
                                long tradeLongVal = nf.parse(trade).longValue();
                                stockTrade.setTradeVol(tradeLongVal);
                                stockTrade.setPrice(nf.parse(price).doubleValue());

                                stockTradesListForSelectedCounter.add(stockTrade);
                                //stockTradeDao.addSingleStockTrade(stockTrade);
                            }

                            i = i + 1;
                        }

                        /* Set process data.*/
                        if (i < 3) {
                            processInfo = "No Record Found";
                        } else {
                            processInfo = (i - 3) + " Record Found";
                        }
                    }
                }

                /* Write the details to a file. TODO Remove */
                writeToFile(stockTradesListForSelectedCounter, pathToFolder ,  date , tradeProcess.getTicker());

                /* Update As Processed */
                processedCountersStr = processedCountersStr + " : " + tradeProcess.getTicker();
                tradeProcess.setProcessed("YES");
                tradeProcess.setInfo(processInfo);
                eodDataDao.updateTradeProcess(tradeProcess);
            }

            return processedCountersStr;
        }
    }

    private void writeToFile(List<StockTrade> stockTradesListForSelectedCounter, String filePath, String date , String ticker) throws IOException {

        String creatingFile = filePath + "/" + date + "_"+ ticker.replace('.','_');

        FileWriter fstream = new FileWriter(creatingFile);
        BufferedWriter out = new BufferedWriter(fstream);
        StringBuilder pageContent = new StringBuilder();

        for (StockTrade stockTrade : stockTradesListForSelectedCounter) {

            String line = stockTrade.getDate() + "|" + stockTrade.getTicker() + "|" +
                    stockTrade.getTime() + "|" + stockTrade.getPrice() + "|" + stockTrade.getTradeVol() + "\n";

            pageContent = pageContent.append(line);
        }

        log.debug("Writing Trade Details to File : \n" + pageContent);
        out.write(pageContent.toString());
        out.close();

    }
}
