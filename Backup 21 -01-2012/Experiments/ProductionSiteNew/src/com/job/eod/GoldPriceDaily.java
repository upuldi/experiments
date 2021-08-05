package com.job.eod;

import com.dao.GoldPriceDao;
import com.domain.GoldPrice;
import com.framework.SpringContext;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.util.UniversalTime;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 1/19/12
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoldPriceDaily {

    private static Logger log = Logger.getLogger(GoldPriceDaily.class);
    private GoldPriceDao goldPriceDao;

    public void setGoldPriceDao(GoldPriceDao goldPriceDao) {
        this.goldPriceDao = goldPriceDao;
    }

    public String processAllGoldPrice() throws IOException, ParseException {

        log.debug("Process EOD Trading Summery...");
        String returnString = "SUCCESS";

        final WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);
        webClient.setAppletEnabled(false);
        webClient.setCssEnabled(false);

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String goldPriceSLUrl = properties.getProperty("commodity.gold.sl.url");
        final HtmlPage page = webClient.getPage(goldPriceSLUrl);
        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();


        int tableCount = 0;
        while (iteratorHtmlElement.hasNext()) {

            HtmlElement currentElement = iteratorHtmlElement.next();

            if (currentElement instanceof HtmlTable) {


                log.debug(" Table Found  ......................" + tableCount);

                HtmlTable htmlTable = (HtmlTable) currentElement;

                if (7 == tableCount) {

                    log.debug(" Table Found  ");
                    List<HtmlTableRow> rowList = htmlTable.getRows();
                    int rowCount = 0;

                    for (HtmlTableRow row : rowList) {

                        if (rowCount == 0) {
                            rowCount = rowCount + 1;
                            continue;
                        }

                        String date = row.getCell(0).asText();
                        String goldOunce = row.getCell(1).asText();
                        String goldPound = row.getCell(2).asText();
                        String gramKarat24 = row.getCell(3).asText();
                        String gramKarat22 = row.getCell(4).asText();
                        String gramKarat21 = row.getCell(5).asText();
                        String gramKarat18 = row.getCell(6).asText();
                        String gramKarat14 = row.getCell(7).asText();

                        log.debug(" Date is " + date);
                        log.debug(" goldOunce is " + goldOunce);
                        log.debug(" goldPound is " + goldPound);
                        log.debug(" gramKarat24 is " + gramKarat24);
                        log.debug(" gramKarat22 is " + gramKarat22);
                        log.debug(" gramKarat21 is " + gramKarat21);
                        log.debug(" gramKarat18 is " + gramKarat18);
                        log.debug(" gramKarat14 is " + gramKarat14);

                        GoldPrice goldPrice = new GoldPrice();

                        String dateMonth = date.substring(date.length() - 2, date.length());
                        String dateYear = null;

                        if (dateMonth.equalsIgnoreCase("12")) {
                            dateYear = "-2011";
                        } else {
                            dateYear = "-2012";
                        }

                        String dateFullString = date + dateYear;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        goldPrice.setDate(simpleDateFormat.parse(dateFullString));
                        goldPrice.setGoldOunce(goldOunce);
                        goldPrice.setGoldPound(goldPound);
                        goldPrice.setGramKarat24(gramKarat24);
                        goldPrice.setGramKarat22(gramKarat22);
                        goldPrice.setGramKarat22(gramKarat22);
                        goldPrice.setGramKarat21(gramKarat21);
                        goldPrice.setGramKarat18(gramKarat18);
                        goldPrice.setGramKarat14(gramKarat14);

                        goldPriceDao.saveGoldPrice(goldPrice);
                        rowCount = rowCount + 1;
                    }
                }
                tableCount = tableCount + 1;
            }
        }
        return returnString;
    }

    public String processDayGoldPrice() throws IOException, ParseException {

        log.debug("Process EOD Trading Summery...");
        String returnString = "SUCCESS";

        final WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);
        webClient.setAppletEnabled(false);
        webClient.setCssEnabled(false);

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String goldPriceSLUrl = properties.getProperty("commodity.gold.sl.url");
        final HtmlPage page = webClient.getPage(goldPriceSLUrl);
        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();


        int tableCount = 0;
        while (iteratorHtmlElement.hasNext()) {

            HtmlElement currentElement = iteratorHtmlElement.next();

            if (currentElement instanceof HtmlTable) {


                log.debug(" Table Found  ......................" + tableCount);

                HtmlTable htmlTable = (HtmlTable) currentElement;

                if (7 == tableCount) {

                    log.debug(" Table Found  ");
                    List<HtmlTableRow> rowList = htmlTable.getRows();
                    int rowCount = 0;

                    for (HtmlTableRow row : rowList) {

                        if (rowCount == 0) {
                            rowCount = rowCount + 1;
                            continue;
                        }

                        /* Process Only The first row */
                        if (rowCount == 1) {

                            String date = row.getCell(0).asText();
                            String goldOunce = row.getCell(1).asText();
                            String goldPound = row.getCell(2).asText();
                            String gramKarat24 = row.getCell(3).asText();
                            String gramKarat22 = row.getCell(4).asText();
                            String gramKarat21 = row.getCell(5).asText();
                            String gramKarat18 = row.getCell(6).asText();
                            String gramKarat14 = row.getCell(7).asText();

                            log.debug(" Date is " + date);
                            log.debug(" goldOunce is " + goldOunce);
                            log.debug(" goldPound is " + goldPound);
                            log.debug(" gramKarat24 is " + gramKarat24);
                            log.debug(" gramKarat22 is " + gramKarat22);
                            log.debug(" gramKarat21 is " + gramKarat21);
                            log.debug(" gramKarat18 is " + gramKarat18);
                            log.debug(" gramKarat14 is " + gramKarat14);

                            GoldPrice goldPrice = new GoldPrice();

                            String dateMonth = date.substring(date.length() - 2, date.length());
                            String dateYear = null;

                            if (dateMonth.equalsIgnoreCase("12")) {
                                dateYear = "-2011";
                            } else {
                                dateYear = "-2012";
                            }

                            String dateFullString = date + dateYear;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            // Date dateConsidering = simpleDateFormat.parse(dateFullString);

                            DateTime dateCSE = UniversalTime.getTime();
                            String dateColomboNow = dateCSE.getDayOfMonth() + "-" + dateCSE.getMonthOfYear();


                            if (date.equalsIgnoreCase(dateColomboNow)) {

                                goldPrice.setDate(simpleDateFormat.parse(dateFullString));
                                goldPrice.setGoldOunce(goldOunce);
                                goldPrice.setGoldPound(goldPound);
                                goldPrice.setGramKarat24(gramKarat24);
                                goldPrice.setGramKarat22(gramKarat22);
                                goldPrice.setGramKarat22(gramKarat22);
                                goldPrice.setGramKarat21(gramKarat21);
                                goldPrice.setGramKarat18(gramKarat18);
                                goldPrice.setGramKarat14(gramKarat14);
                                goldPriceDao.saveGoldPrice(goldPrice);
                            }
                        }

                        if (rowCount > 1) {
                            break;
                        }
                        rowCount = rowCount + 1;
                    }
                }
                tableCount = tableCount + 1;
            }
        }
        return returnString;
    }
}
