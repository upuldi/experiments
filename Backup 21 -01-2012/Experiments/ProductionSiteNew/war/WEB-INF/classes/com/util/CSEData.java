package com.util;

import com.domain.MarketStatus;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 5/2/11
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class CSEData {


    private static Logger log = Logger.getLogger(CSEData.class);

    public static MarketStatus getMarketStatusData() throws IOException, MalformedURLException {

        MarketStatus marketStatusObj = new MarketStatus();
        ;

        try {

            // Construct data
            String data = URLEncoder.encode("callCount", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
            data += "&" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("/header.htm", "UTF-8");
            data += "&" + URLEncoder.encode("httpSessionId", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
            data += "&" + URLEncoder.encode("scriptSessionId", "UTF-8") + "=" + URLEncoder.encode("26E1945B4904AB03366074875066448D373", "UTF-8");
            data += "&" + URLEncoder.encode("c0-scriptName", "UTF-8") + "=" + URLEncoder.encode("MarketDataJS", "UTF-8");
            data += "&" + URLEncoder.encode("c0-methodName", "UTF-8") + "=" + URLEncoder.encode("getMarketStatus", "UTF-8");
            data += "&" + URLEncoder.encode("c0-id", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
            data += "&" + URLEncoder.encode("batchId", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");


            // Send data
            URL url = new URL("http://www.cse.lk/dwr/call/plaincall/MarketDataJS.getMarketStatus.dwr");
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

            conn.setRequestProperty("POSTDATA", "callCount=1\\n" +
                    "page=/header.htm\\n" +
                    "httpSessionId=\\n" +
                    "scriptSessionId=\\n" +
                    "c0-scriptName=MarketDataJS\\n" +
                    "c0-methodName=getMarketStatus\\n" +
                    "c0-id=0\\n" +
                    "batchId=1");


            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            int status = ((HttpURLConnection) conn).getResponseCode();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder completeResponse = new StringBuilder();


            while ((line = rd.readLine()) != null) {
                //log.debug(line);
                completeResponse.append(line);
            }
/*
            String contentType = conn.getHeaderField("Content-Type");
            String charset = null;
            for (String param : contentType.replace(" ", "").split(";")) {
                if (param.startsWith("charset=")) {
                    charset = param.split("=", 2)[1];
                    break;
                }
            }

            if (charset != null) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
                    for (String line; (line = reader.readLine()) != null;) {
                        System.out.println(line);
                    }
                } finally {
                    if (reader != null) try {
                        reader.close();
                    } catch (IOException logOrIgnore) {
                    }
                }
            } else {
                // It's likely binary content, use InputStream/OutputStream.
            }
*/

            wr.close();
            rd.close();

            log.debug("Complete Response : " + completeResponse.toString());

            String valueSec = completeResponse.substring(completeResponse.indexOf("(") + 1, completeResponse.indexOf(")"));
            log.debug(valueSec);

            String[] values = valueSec.split("[,]");

            for (String split : values) {

                if (split.contains("~")) {

                    String marketStatus = "";
                    String marketDate = "";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");

                    marketStatus = split.substring(1, split.indexOf("~"));
                    marketDate = split.substring((split.indexOf("~") + 1), (split.length() - 1));

                    marketStatusObj.setStatus(marketStatus);
                    marketStatusObj.setDate(marketDate);
                    marketStatusObj.setDateObj(simpleDateFormat.parse(marketDate));

                    log.debug("Market Status is : " + marketStatus);
                    log.debug("Market Date is : " + marketDate);


                }
            }

        } catch (Exception e) {
            log.debug("Exception Occured ...... " + e.getMessage());
        }

        return marketStatusObj;
    }

}
