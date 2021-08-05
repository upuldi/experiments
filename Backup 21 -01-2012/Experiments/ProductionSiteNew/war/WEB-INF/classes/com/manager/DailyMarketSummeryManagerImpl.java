package com.manager;

import com.domain.DailyMarketSummery;
import com.framework.SpringContext;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.util.UniversalTime;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class DailyMarketSummeryManagerImpl implements DailyMarketSummeryManager {

    private static Logger log = Logger.getLogger(DailyMarketSummeryManagerImpl.class);

    public DailyMarketSummery getDailyTradingSummeryForTheDay() throws IOException {


        DailyMarketSummery dailyTradingSummery = new DailyMarketSummery();

        Properties properties = (Properties) SpringContext.getBean("appProperties");

        Date dateToday = null;
        dateToday = UniversalTime.getTime().toDate();
        String DATE_FORMAT = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String dateString = sdf.format(dateToday);

        String servletURL = properties.getProperty("app.servlet.trade.url");
//        String servletURL = "http://localhost:9090/CSERT/tradeSummery";
        log.debug("Servlet URL : " + servletURL);


        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage(servletURL);

        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();

        while (iteratorHtmlElement.hasNext()) {


            HtmlElement currentElement = iteratorHtmlElement.next();

            if (currentElement instanceof HtmlTable) {


                log.debug("HTML TABLE FOUND");

                HtmlTable htmlTable = (HtmlTable) currentElement;

                List<HtmlTableRow> rowList = htmlTable.getRows();

                dailyTradingSummery.setDate(dateString);

                /* CSE ALL SHARE INDEX TODAY */
                log.debug(rowList.get(2).getCell(0).getTextContent() + " : " + rowList.get(2).getCell(1).getTextContent());
                dailyTradingSummery.setCseAllShareIndex(rowList.get(2).getCell(1).getTextContent());

                /* MILANKA PRICE INDEX TODAY*/
                log.debug(rowList.get(3).getCell(0).getTextContent() + " : " + rowList.get(3).getCell(1).getTextContent());
                dailyTradingSummery.setMilankaPriceIndex(rowList.get(3).getCell(1).getTextContent());

                /* ASPI and MPI Change. */
                String aspiLastDate = rowList.get(2).getCell(2).getTextContent();
                String mpiLastDate = rowList.get(3).getCell(2).getTextContent();

                log.debug( "ASPI LAST DATE : " + aspiLastDate);
                log.debug( "MPI LAST DATE : " + mpiLastDate);
                NumberFormat numberFormat = NumberFormat.getInstance();

                if (null != aspiLastDate && null != mpiLastDate) {


                    try {

                        double aspiValueToday = numberFormat.parse(dailyTradingSummery.getCseAllShareIndex()).doubleValue();
                        double aspiValueLastDate = numberFormat.parse(aspiLastDate).doubleValue();

                        double aspiChange = aspiValueToday - aspiValueLastDate;
                        double aspiChangePresentage = (aspiChange/aspiValueLastDate) * 100;

                        double mpiValueToday = numberFormat.parse(dailyTradingSummery.getMilankaPriceIndex()).doubleValue();
                        double mpiValueLastDate = numberFormat.parse(mpiLastDate).doubleValue();

                        double mpiChange = mpiValueToday - mpiValueLastDate;
                        double mpiChangePresentage = (mpiChange/mpiValueLastDate) * 100;

                        BigDecimal bdASPIChange = new BigDecimal(aspiChange).setScale(2, RoundingMode.HALF_EVEN);
                        double aspiChangeRounded = bdASPIChange.doubleValue();

                        BigDecimal bdMPIChange = new BigDecimal(mpiChange).setScale(2, RoundingMode.HALF_EVEN);
                        double mpiChangeRounded = bdMPIChange.doubleValue();

                        BigDecimal bdASPIChangePresentage = new BigDecimal(aspiChangePresentage).setScale(2, RoundingMode.HALF_EVEN);
                        double aspiChangePresntageRounded = bdASPIChangePresentage.doubleValue();

                        BigDecimal bdMPIChangePresentage = new BigDecimal(mpiChangePresentage).setScale(2, RoundingMode.HALF_EVEN);
                        double mpiChangePresentageRounded = bdMPIChangePresentage.doubleValue();

                        dailyTradingSummery.setAspiChange(aspiChangeRounded+"");
                        dailyTradingSummery.setMpiChange(mpiChangeRounded+"");
                        dailyTradingSummery.setAspiChangePresentage(aspiChangePresntageRounded+"");
                        dailyTradingSummery.setMpiChangePresentage(mpiChangePresentageRounded+"");


                    } catch (ParseException e) {
                        log.debug("Error Occured While Calculating the Index change : " + e.getMessage());
                    }
                }




                /* TRI ON ALL SHARES */
                log.debug(rowList.get(4).getCell(0).getTextContent() + " : " + rowList.get(4).getCell(1).getTextContent());
                dailyTradingSummery.setTriOnAllShares(rowList.get(4).getCell(1).getTextContent());

                /* TRI ON MILANKA SHARES */
                log.debug(rowList.get(5).getCell(0).getTextContent() + " : " + rowList.get(5).getCell(1).getTextContent());
                dailyTradingSummery.setTriOnMilankaShares(rowList.get(5).getCell(1).getTextContent());

                /* MARKET VALUE OF TURNOVER(Rs.) 7 */
                log.debug(rowList.get(7).getCell(0).getTextContent() + " : " + rowList.get(7).getCell(1).getTextContent());
                dailyTradingSummery.setMarketValueOfTurnover_rs(rowList.get(7).getCell(1).getTextContent());

                /* TRADES (NO.)	 */
                log.debug(rowList.get(8).getCell(0).getTextContent() + " : " + rowList.get(8).getCell(1).getTextContent());
                dailyTradingSummery.setTradesNo(rowList.get(8).getCell(1).getTextContent());

                /* DOMESTIC */
                log.debug(rowList.get(9).getCell(0).getTextContent() + " : " + rowList.get(9).getCell(1).getTextContent());
                dailyTradingSummery.setDomesticNo(rowList.get(9).getCell(1).getTextContent());

                /* FOREIGN */
                log.debug(rowList.get(10).getCell(0).getTextContent() + " : " + rowList.get(10).getCell(1).getTextContent());
                dailyTradingSummery.setForeignNo(rowList.get(10).getCell(1).getTextContent());

                /* EQUITY VALUE OF TURNOVER(Rs.) 12 */
                log.debug(rowList.get(12).getCell(0).getTextContent() + " : " + rowList.get(12).getCell(1).getTextContent());
                dailyTradingSummery.setEquityValueOfTurnoverRs(rowList.get(12).getCell(1).getTextContent());

                /* DOMESTIC PURCHASE */
                log.debug(rowList.get(13).getCell(0).getTextContent() + " : " + rowList.get(13).getCell(1).getTextContent());
                dailyTradingSummery.setDomesticPurchaseRs(rowList.get(13).getCell(1).getTextContent());

                /* DOMESTIC SALES */
                log.debug(rowList.get(14).getCell(0).getTextContent() + " : " + rowList.get(14).getCell(1).getTextContent());
                dailyTradingSummery.setDomesticSalesRs(rowList.get(14).getCell(1).getTextContent());

                /* FOREIGN PURCHASE */
                log.debug(rowList.get(15).getCell(0).getTextContent() + " : " + rowList.get(15).getCell(1).getTextContent());
                dailyTradingSummery.setForeignPurchaseRs(rowList.get(15).getCell(1).getTextContent());

                /* FOREIGN SALES */
                log.debug(rowList.get(16).getCell(0).getTextContent() + " : " + rowList.get(16).getCell(1).getTextContent());
                dailyTradingSummery.setForeignSalesRs(rowList.get(16).getCell(1).getTextContent());

                /* VOLUME OF TURNOVER(NO.) 18 */
                log.debug(rowList.get(18).getCell(0).getTextContent() + " : " + rowList.get(18).getCell(1).getTextContent());
                dailyTradingSummery.setVolumeOfTurnoverNo(rowList.get(18).getCell(1).getTextContent());

                /* DOMESTIC */
                log.debug(rowList.get(19).getCell(0).getTextContent() + " : " + rowList.get(19).getCell(1).getTextContent());
                dailyTradingSummery.setVolumeOfDomesticNo(rowList.get(19).getCell(1).getTextContent());

                /* FOREIGN */
                log.debug(rowList.get(20).getCell(0).getTextContent() + " : " + rowList.get(20).getCell(1).getTextContent());
                dailyTradingSummery.setVolumeOfForeginNo(rowList.get(20).getCell(1).getTextContent());

            }
        }

         page.cleanUp();
        return dailyTradingSummery;

    }
}
