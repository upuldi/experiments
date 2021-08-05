package com.util;

import com.domain.MarketStatus;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/23/11
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class TESTCSELinkGETIndiciesToday {


    private static Logger log = Logger.getLogger(TestCSELinkTopGainers.class);


    public static MarketStatus getMarketStatusData() throws IOException, MalformedURLException {

        MarketStatus marketStatusObj = new MarketStatus();


        try {

            // Construct data
            String data = URLEncoder.encode("callCount", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
            data += "&" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("/header.htm", "UTF-8");
            data += "&" + URLEncoder.encode("httpSessionId", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
            data += "&" + URLEncoder.encode("scriptSessionId", "UTF-8") + "=" + URLEncoder.encode("26E1945B4904AB03366074875066448D373", "UTF-8");
            data += "&" + URLEncoder.encode("c0-scriptName", "UTF-8") + "=" + URLEncoder.encode("MarketDataJS", "UTF-8");
            data += "&" + URLEncoder.encode("c0-methodName", "UTF-8") + "=" + URLEncoder.encode("getIndicesToday", "UTF-8");
            data += "&" + URLEncoder.encode("c0-id", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
            data += "&" + URLEncoder.encode("batchId", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");


            // Send data
            //URL url = new URL("http://www.cse.lk/dwr/call/plaincall/MarketDataJS.getMarketStatus.dwr");
            URL url = new URL("http://www.cse.lk/dwr/call/plaincall/MarketDataJS.getIndicesToday.dwr");
            URLConnection conn = url.openConnection();
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

            Map<String,List<String>> k = ((HttpURLConnection) conn).getHeaderFields();

            for (Map.Entry<String,List<String>> e: k.entrySet()) {

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

            String completeResponseString = completeResponse.toString();

            String[] springArray = completeResponseString.split("[;]");

            int count = 0;
            for (String s : springArray) {

                log.debug(count +" : "+ s);

                if (s.contains("s2.v")) {
                    log.debug("MPI FOUND : " + s);
                }
                if (s.contains("s22.v")) {
                    log.debug("ASPI FOUND : " + s);
                }


                count = count + 1;

            }



        } catch (Exception e) {
            log.debug("Exception Occured ...... " + e.getMessage());
        }

        return marketStatusObj;
    }

    public static void main(String[] args) throws IOException {

        getMarketStatusData();


    }





}
