package com.job.eod;

import au.com.bytecode.opencsv.CSVReader;
import com.dao.EODDataDao;
import com.domain.CSEListedCompany;
import com.domain.EODData;
import com.domain.MarketStatus;
import com.framework.SpringContext;
import com.util.CSEData;
import com.util.UniversalTime;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/22/11
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODMarketData {

    private static Logger log = Logger.getLogger(EODMarketData.class);
    private String EOD_DATA_TXT_FILE = "EOD-Data.txt";
    private EODDataDao eodDataDao;

    public void setEodDataDao(EODDataDao eodDataDao) {
        this.eodDataDao = eodDataDao;
    }

    public String processEODMarketData() throws ParseException, IOException {

        log.debug(" Processing EOD market data");
        String returnString = "SUCCESS";

        MarketStatus marketStatus = CSEData.getMarketStatusData();
        String date = marketStatus.getDate();
        Date marketDateObj = marketStatus.getDateObj();
        Date nowDate = new Date();

        if (nowDate.getDate() == marketDateObj.getDate()) {

            Properties properties = (Properties) SpringContext.getBean("appProperties");
            String cseStockDailCSVUrl = properties.getProperty("cse.stock.daily.csv.url");

            URL cseStockOverview = new URL(cseStockDailCSVUrl);
            URLConnection urlConnection = cseStockOverview.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String DATE_FORMAT = "yyyyMMdd";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

            Date currentTime = UniversalTime.getTime().toDate();
            String dateString = sdf.format(currentTime);

            log.debug("DATE : " + dateString);

            CSVReader reader = new CSVReader(bufferedReader);
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {

                NumberFormat nf = NumberFormat.getInstance();

                String ticker = nextLine[1];

                long recordVolume = 0;

                if (null != nextLine[2] && !(nextLine[2].isEmpty()) ) {
                    recordVolume = nf.parse(nextLine[2]).longValue();
                }

                EODData eodData = new EODData();
                eodData.setTicker(ticker);
                eodData.setDate(dateString);
                eodData.setTrade(nextLine[3]);
                eodData.setOpen(nextLine[5]);
                eodData.setHigh(nextLine[6]);
                eodData.setLow(nextLine[7]);
                eodData.setClose(nextLine[8]);
                eodData.setChange(nextLine[9]);
                eodData.setChangePresentage(nextLine[10]);

                eodData.setVol(recordVolume);

                eodDataDao.saveEODData(eodData);
            }

        } else {
            returnString = "NOT A MARKET DAY";
        }

        return returnString;

    }

    public String processListedCompanyListData() throws ParseException, IOException {

        log.debug(" Processing EOD market data");
        String returnString = "SUCCESS";

        MarketStatus marketStatus = CSEData.getMarketStatusData();
        String date = marketStatus.getDate();
        Date marketDateObj = marketStatus.getDateObj();
        Date nowDate = new Date();

        if (nowDate.getDate() == marketDateObj.getDate()) {

            Properties properties = (Properties) SpringContext.getBean("appProperties");
            String cseStockDailCSVUrl = properties.getProperty("cse.stock.daily.csv.url");

            URL cseStockOverview = new URL(cseStockDailCSVUrl);
            URLConnection urlConnection = cseStockOverview.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String DATE_FORMAT = "yyyyMMdd";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

            Date currentTime = UniversalTime.getTime().toDate();
            String dateString = sdf.format(currentTime);

            log.debug("DATE : " + dateString);

            CSVReader reader = new CSVReader(bufferedReader);
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {

                NumberFormat nf = NumberFormat.getInstance();

                String companyName = nextLine[0];
                String ticker = nextLine[1];

                CSEListedCompany cseListedCompany = new CSEListedCompany();
                cseListedCompany.setCompanyName(companyName);
                cseListedCompany.setStockCode(ticker);

                eodDataDao.saveCSEListedCompany(cseListedCompany);
            }

        } else {
            returnString = "NOT A MARKET DAY";
        }

        return returnString;
    }
}
