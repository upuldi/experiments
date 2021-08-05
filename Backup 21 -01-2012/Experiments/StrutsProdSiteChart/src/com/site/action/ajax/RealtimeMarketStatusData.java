package com.site.action.ajax;

import com.domain.MarketStatus;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 12/11/11
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class RealtimeMarketStatusData extends ActionSupport implements ModelDriven {

    private static Logger log = Logger.getLogger(RealtimeMarketStatusData.class);

    private MarketStatus marketStatus;


    public String getRTIndexData() {

        try {

            String marketSummeryUrl = "http://www.cse.lk/dwr/call/plaincall/MarketDataJS.getMarketSummary.dwr";
            String positiveContributorsUrl = "http://www.cse.lk/dwr/call/plaincall/MarketDataJS.getPositiveContributorsCount.dwr";
            String negativeContributorsUrl = "http://www.cse.lk/dwr/call/plaincall/MarketDataJS.getNegativeContributorsCount.dwr";

            String marketSummeryMethodName = "getMarketSummary";
            String positiveContributorsMethodName = "getPositiveContributorsCount";
            String negativeContributorsMethodName = "getNegativeContributorsCount";


            StringBuilder completeResponseMarketSummery = getCSEDataString(marketSummeryUrl, marketSummeryMethodName);
            String completeResponseString = completeResponseMarketSummery.toString();

            String valuesSection = completeResponseString.substring(completeResponseString.indexOf("{"),
                    completeResponseString.indexOf("}"));

            log.debug("Values Section : " + valuesSection);

            String[] springArray = valuesSection.split("[,]");

            int count = 0;
            for (String s : springArray) {
                log.debug(count + " : " + s);
                count = count + 1;
            }

            String volumeValue = springArray[0].substring(springArray[0].indexOf(":")+1, springArray[0].length());
            NumberFormat withOutdecimalPoint = new DecimalFormat("###,###,###,###");
            NumberFormat bigNumber = new DecimalFormat("############");
            volumeValue = withOutdecimalPoint.format(bigNumber.parse(volumeValue));
            log.debug("Volume Value " + volumeValue);

            String turnOverValue = springArray[1].substring(springArray[0].indexOf(":"), springArray[1].length());
            log.debug("turnOverValue " + turnOverValue);

            NumberFormat f = new DecimalFormat("0000.0000000000E00");
            NumberFormat other = new DecimalFormat("###,###,###,###.00");
            String formattedTurnoverValue  = other.format(f.parse(turnOverValue).doubleValue());
            log.debug("turnOverValue " + formattedTurnoverValue);

            //-----------------------------------------

            StringBuilder completeResponsePositiveContributors = getCSEDataString(positiveContributorsUrl, positiveContributorsMethodName);
            log.debug(" POSITIVE CONTRIBUTION  " + completeResponsePositiveContributors);

            String valueSectionPositive = completeResponsePositiveContributors.substring(
                    completeResponsePositiveContributors.indexOf("("), completeResponsePositiveContributors.indexOf(")"));

            log.debug("Value Section 2 " + valueSectionPositive);
            String[] positiveValuesArray = valueSectionPositive.split("[,]");

            log.debug("POSITIVE Value is : " + positiveValuesArray[2]);
            String positiveContributorsValue = positiveValuesArray[2];


            //-----------------------------------------

            StringBuilder completeResponseNegativeContributors = getCSEDataString(negativeContributorsUrl, negativeContributorsMethodName);
            log.debug(" NEGATIVE CONTRIBUTION " + completeResponseNegativeContributors);

            String valueSectionNegative = completeResponseNegativeContributors.substring(
                    completeResponseNegativeContributors.indexOf("("), completeResponseNegativeContributors.indexOf(")"));

            log.debug("Value Section Negative " + valueSectionNegative);
            String[] negativeValuesArray = valueSectionNegative.split("[,]");

            log.debug("Negative Value is : " + negativeValuesArray[2]);
            String negativeContributorsValue = negativeValuesArray[2];

            marketStatus = new MarketStatus();
            marketStatus.setTurnover(formattedTurnoverValue);
            marketStatus.setVolume(volumeValue);
            marketStatus.setPositiveContributors(positiveContributorsValue);
            marketStatus.setNegativeContributors(negativeContributorsValue);


        } catch (Exception e) {
            log.error("Exception Occured ...... " + e.getMessage());
        }

        return SUCCESS;
    }

    private StringBuilder getCSEDataString(String url, String methodName) throws IOException {

        String data = URLEncoder.encode("callCount", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
        data += "&" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("/header.htm", "UTF-8");
        data += "&" + URLEncoder.encode("httpSessionId", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
        data += "&" + URLEncoder.encode("scriptSessionId", "UTF-8") + "=" + URLEncoder.encode("26E1945B4904AB03366074875066448D373", "UTF-8");
        data += "&" + URLEncoder.encode("c0-scriptName", "UTF-8") + "=" + URLEncoder.encode("MarketDataJS", "UTF-8");
        data += "&" + URLEncoder.encode("c0-methodName", "UTF-8") + "=" + URLEncoder.encode(methodName, "UTF-8");
        data += "&" + URLEncoder.encode("c0-id", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
        data += "&" + URLEncoder.encode("batchId", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");

        URL urlMarketSummery = new URL(url);
        URLConnection conn = urlMarketSummery.openConnection();
        conn.setDoOutput(true);
        conn.setDoOutput(true);

        conn.setRequestProperty("Host", "www.cse.lk");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-us,en;q=0.5");
        conn.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
        conn.setRequestProperty("Referer", "http://www.cse.lk/header.htm");
        conn.setRequestProperty("Content-Length", "171");

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        int status = ((HttpURLConnection) conn).getResponseCode();
        log.debug("Response Status : " + status);

        /* Display Response Headers. */
        Map<String, List<String>> k = ((HttpURLConnection) conn).getHeaderFields();
        for (Map.Entry<String, List<String>> e : k.entrySet()) {

            log.debug("Header Key " + e.getKey());
            for (String s : e.getValue()) {
                log.debug("Values" + s);
            }
        }

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder completeResponse = new StringBuilder();


        while ((line = rd.readLine()) != null) {
            //log.debug(line);
            completeResponse.append(line);
        }

        wr.close();
        rd.close();

        log.debug("Complete Response : " + completeResponse.toString());
        return completeResponse;
    }

    public void setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
    }

    public Object getModel() {
        return marketStatus;
    }
}

