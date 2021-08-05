package com.datasetup;

import com.domain.DailyMarketSummery;
import com.domain.EODData;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.RefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/25/11
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockSync {

    public static void main(String[] args) throws IOException, ParseException {

        System.out.println(" Starting....");

        // Create and initialize WebClient object
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_2);
        webClient.setThrowExceptionOnScriptError(false);
        webClient.setRefreshHandler(new RefreshHandler() {
            public void handleRefresh(Page page, URL url, int arg) throws IOException {
                System.out.println("handleRefresh");
            }

        });

        // visit Yahoo Mail login page and get the Form object
        HtmlPage page = (HtmlPage) webClient.getPage("http://www.slstock.com/stock/");
        HtmlForm form = page.getFormByName("Login");

        // Enter login and passwd
        form.getInputByName("username").setValueAttribute("guest");
        form.getInputByName("password").setValueAttribute("guest");

        // Click "Sign In" button/link
//	    page = (HtmlPage) form.getInputByValue("Sign In").click();
//	    page = (HtmlPage) form.getButtonByName(".save").click();
        page = (HtmlPage) form.getInputByName("B1").click();

        System.out.println(" ****************************** " + page.getTitleText());

        //Daily Transactions
        // Click "Inbox" link

        FrameWindow frameWindow = page.getFrameByName("main");
        HtmlPage frameEnclosedPage = frameWindow.getEnclosingPage().getPage();


        System.out.println(" TEXT " + frameEnclosedPage.asText());


        //
        //Page page2 = frameEnclosedPage.getInputByValue("Daily Transactions").click();


        List<FrameWindow> ff = page.getFrames();

        for (FrameWindow frameWindowD : ff) {
            System.out.println(frameWindowD.getName());
        }

        HtmlPage pageNeeded = (HtmlPage) ff.get(1).getEnclosedPage();

        List<HtmlAnchor> an = pageNeeded.getAnchors();

        HtmlPage transactionPage = null;

        System.out.println(transactionPage);


        for (HtmlAnchor htmlAnchor : an) {

            System.out.println(":" + htmlAnchor.getTextContent() + ":");

            if (htmlAnchor.getTextContent().trim().contains("Daily Transactions")) {

                System.out.println("Transaction Link Found...");
                transactionPage = htmlAnchor.click();
            }

        }

        System.out.println(" ********* TransactionPage : " + transactionPage);

        String dateFormatString = "yyyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString);

        HtmlForm dataForm = transactionPage.getFormByName("Data");

        dataForm.getInputByName("SystemDate").setValueAttribute("20110625");
        dataForm.getInputByName("OrderBy").setValueAttribute("1");
        dataForm.getInputByName("TxnDate").setValueAttribute("2011-06-09");

        HtmlPage resultsPage = (HtmlPage) dataForm.getInputByName("B2").click();

        System.out.println(" RESULTS ******* " + resultsPage);
        Iterator<HtmlElement> iteratorHtmlElement = resultsPage.getAllHtmlChildElements().iterator();


        int tableCount = 0;


        while (iteratorHtmlElement.hasNext()) {


            HtmlElement currentElement = iteratorHtmlElement.next();

            if (currentElement instanceof HtmlTable) {

                tableCount = tableCount + 1;

                System.out.println("HTML TABLE FOUND..." + tableCount);

                HtmlTable htmlTable = (HtmlTable) currentElement;
                List<HtmlTableRow> rowList = htmlTable.getRows();

                /* Market Basic Data Table... */
                if (3 == tableCount) {

                    DailyMarketSummery dailyMarketSummery = new DailyMarketSummery();
                    dailyMarketSummery.setDate("2011-06-09");
                    dailyMarketSummery.setCseAllShareIndex(rowList.get(0).getCell(1).getTextContent());
                    dailyMarketSummery.setMilankaPriceIndex(rowList.get(1).getCell(1).getTextContent());
                    dailyMarketSummery.setVolumeOfTurnoverNo(rowList.get(2).getCell(1).getTextContent());
                    dailyMarketSummery.setMarketValueOfTurnover_rs(rowList.get(3).getCell(1).getTextContent());
                    dailyMarketSummery.setEquityValueOfTurnoverRs(rowList.get(3).getCell(1).getTextContent());

                    System.out.println(rowList.get(0).getCell(0).getTextContent() + " : " + rowList.get(0).getCell(1).getTextContent());
                    System.out.println(rowList.get(1).getCell(0).getTextContent() + " : " + rowList.get(1).getCell(1).getTextContent());
                    System.out.println(rowList.get(2).getCell(0).getTextContent() + " : " + rowList.get(2).getCell(1).getTextContent());
                    System.out.println(rowList.get(3).getCell(0).getTextContent() + " : " + rowList.get(3).getCell(1).getTextContent());


                    System.out.println("Daily Market Summery Obj : All " + dailyMarketSummery.getCseAllShareIndex() + " Milanka : " +
                            dailyMarketSummery.getMilankaPriceIndex() + " Volume : " + dailyMarketSummery.getVolumeOfTurnoverNo() + " Turnover : " +
                            dailyMarketSummery.getMarketValueOfTurnover_rs());
                }

                /* Market Values Table...*/
                if (5 == tableCount) {

                    int rowCountStockData = 0;

                    for (HtmlTableRow htmlTableRow : rowList) {

                        /* Quit the first row since it contains the headers */
                        if (rowCountStockData > 0) {

    /*
                            <td height="40" width="205" align="center">Company</a></td>
                            <td width="70" align="center">Stock Code</a></td>
                            <td width="70" align="center">Sector</a></td>
                            <td width="65" align="center">Volume</a></td>
                            <td width="50" align="center">Trades</a></td>
                            <td width="65" align="center">Previouse Closing**</td>
                            <td width="60" align="center">High</td>
                            <td width="60" align="center">Low</td>
                            <td width="65" align="center">Closing Price**</td>
                            <td width="40" align="center">Change</td>
                            <td width="40" align="center">Change %</a></td>
    */

                            EODData eodData = new EODData();
                            eodData.setDate("2011-06-09");

                            NumberFormat nf = NumberFormat.getInstance();
                            long recordVolume = nf.parse(htmlTableRow.getCell(3).getTextContent()).longValue();


                            /* Stock code*/
                            System.out.println(" Stock Code : " + htmlTableRow.getCell(1).getTextContent());
                            eodData.setTicker(htmlTableRow.getCell(1).getTextContent());

                            System.out.print(" Sector : " + htmlTableRow.getCell(2).getTextContent());

                            /* Volume */
                            System.out.print(" Volume : " + htmlTableRow.getCell(3).getTextContent());
                            eodData.setVol(recordVolume);

                            /* Trades */
                            System.out.print(" Trades : " + htmlTableRow.getCell(4).getTextContent());
                            eodData.setTrade(htmlTableRow.getCell(4).getTextContent());

                            /* */
                            System.out.print(" Previouse Closing : " + htmlTableRow.getCell(5).getTextContent());
                           // eodData.setOpen();

                            /* High */
                            System.out.print(" High : " + htmlTableRow.getCell(6).getTextContent());
                            eodData.setHigh(htmlTableRow.getCell(6).getTextContent());

                            /* Low */
                            System.out.print(" Low : " + htmlTableRow.getCell(7).getTextContent());
                            eodData.setLow(htmlTableRow.getCell(7).getTextContent());

                            /* Close*/
                            System.out.print(" Closing Price : " + htmlTableRow.getCell(8).getTextContent());
                            eodData.setClose(htmlTableRow.getCell(8).getTextContent());

                            /* Change*/
                            System.out.print(" Change : " + htmlTableRow.getCell(9).getTextContent());
                            System.out.print(" Change % : " + htmlTableRow.getCell(10).getTextContent());

                        }

                        rowCountStockData = rowCountStockData + 1;
                    }
                }


            }
        }


    }
}
