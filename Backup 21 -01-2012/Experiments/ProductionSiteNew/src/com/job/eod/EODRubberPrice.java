package com.job.eod;

import com.dao.RubberAuctionDao;
import com.datasetup.RubberAuctionDataSetup;
import com.domain.RubberPrice;
import com.framework.SpringContext;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/25/11
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODRubberPrice {

    private static Logger log = Logger.getLogger(EODRubberPrice.class);

    public String processRubberAuctionPrices() throws IOException, ParseException {

        log.info("Processing Rubber Prices....");
        String returnString= "SUCCESS";

        processAllRubberPrices();
//        returnString = processDailyRubberPrices();

        return returnString;
    }

    private String  processDailyRubberPrices() throws ParseException, IOException {

        log.info("Processing Daily Rubber Prices....");
        String returnString = "NO AUCTION DATA FOUND";

        RubberAuctionDao rubberAuctionDao = (RubberAuctionDao) SpringContext.getBean("rubberAuctionDao");

        String dateFormatString = "yyyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString);

        /*Data extraction*/
        Date dateToday = Calendar.getInstance().getTime();

        String rubberAuctionDateString = simpleDateFormat.format(dateToday);
        String auctionURL = "http://www.crtasl.org/prices.php?auction_date=" + rubberAuctionDateString;

        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage(auctionURL);

        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();

        int i = 0;

        while (iteratorHtmlElement.hasNext()) {


            HtmlElement currentElement = iteratorHtmlElement.next();


            if (currentElement instanceof HtmlTable) {

                log.debug(" TABLE FOUND....");

                if (i == 1) {

                    log.debug("RUBBER TABLE FOUND....");

                    HtmlTable htmlTable = (HtmlTable) currentElement;
                    List<HtmlTableRow> rowList = htmlTable.getRows();

                    int length = rowList.size();

                    for (int j = 0; j < length - 1; j++) {

                        if (j > 0) {

                            log.info(rowList.get(j).getCell(0).getTextContent() +
                                    " : " + rowList.get(j).getCell(1).getTextContent() + " : " + rowList.get(j).getCell(2).getTextContent());

                            RubberPrice rubberPrice = new RubberPrice();
                            rubberPrice.setDate(rubberAuctionDateString);
                            rubberPrice.setItem(rowList.get(j).getCell(0).getTextContent());
                            rubberPrice.setPriceLKR(rowList.get(j).getCell(1).getTextContent());
                            rubberPrice.setPriceUSD(rowList.get(j).getCell(2).getTextContent());

                            log.info("Inserting rubber auction data : " + rubberPrice.getItem());
                            rubberAuctionDao.save(rubberPrice);

                            returnString = "AUCTION DATA FOUND";
                        }
                    }

                } else {
                    i = +1;
                }
            }
        }
        log.info("COMPLETED...");
        return returnString;
    }

    private void processAllRubberPrices() throws IOException, ParseException {

        RubberAuctionDataSetup rubberAuctionDataSetup = new RubberAuctionDataSetup();
        rubberAuctionDataSetup.setupRubberAuctionPricesAll();
    }


}
