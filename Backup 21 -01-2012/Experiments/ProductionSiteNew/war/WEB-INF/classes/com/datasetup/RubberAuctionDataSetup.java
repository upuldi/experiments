package com.datasetup;

import com.dao.RubberAuctionDao;
import com.domain.RubberPrice;
import com.framework.SpringContext;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

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
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class RubberAuctionDataSetup {

    private RubberAuctionDao rubberAuctionDao;

    public void setupRubberAuctionPricesAll() throws ParseException, IOException {

        System.out.println("Starting");
        RubberAuctionDao rubberAuctionDao = (RubberAuctionDao) SpringContext.getBean("rubberAuctionDao");

        String dateFormatString = "yyyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString);

        String dareString = "2011-11-29";
        Date startDateString = simpleDateFormat.parse(dareString);

        Calendar c = Calendar.getInstance();
        c.setTime(startDateString);

        Date startDate =  c.getTime();

        System.out.println(startDate);
        System.out.println((Calendar.getInstance().getTime()).after(startDate));

        for(Date d = c.getTime();(Calendar.getInstance().getTime()).after(d);c.add(Calendar.DATE, 1)){

            d = c.getTime();
            String rubberAuctionDateString = simpleDateFormat.format(c.getTime());
            System.out.println(rubberAuctionDateString);


            /*Data extraction*/
            System.out.println("Starting....");

            String auctionURL = "http://www.crtasl.org/prices.php?auction_date="+rubberAuctionDateString;

            final WebClient webClient = new WebClient();
            final HtmlPage page = webClient.getPage(auctionURL);

            Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();

            int i =0;

            while (iteratorHtmlElement.hasNext()) {


                HtmlElement currentElement = iteratorHtmlElement.next();


                if (currentElement instanceof HtmlTable) {

                     System.out.println("TABLE FOUND....");

                    if (i==1) {

                        System.out.println("GUSS TABLE FOUND....");

                        HtmlTable htmlTable = (HtmlTable) currentElement;
                        List<HtmlTableRow> rowList = htmlTable.getRows();

                        int length = rowList.size();

                        for (int j=0;j<length-1;j++) {

                            if (j>0) {

                                System.out.println(rowList.get(j).getCell(0).getTextContent() +
                                        " : " + rowList.get(j).getCell(1).getTextContent()+ " : " + rowList.get(j).getCell(2).getTextContent());

                                RubberPrice rubberPrice = new RubberPrice();
                                rubberPrice.setDate(rubberAuctionDateString);
                                rubberPrice.setItem(rowList.get(j).getCell(0).getTextContent());
                                rubberPrice.setPriceLKR(rowList.get(j).getCell(1).getTextContent());
                                rubberPrice.setPriceUSD(rowList.get(j).getCell(2).getTextContent());

                                rubberAuctionDao.save(rubberPrice);

                            }
                        }

                    }else {
                        i =+ 1;
                    }
                }
            }

            System.out.println("DONE.....");
        }

    }

}
