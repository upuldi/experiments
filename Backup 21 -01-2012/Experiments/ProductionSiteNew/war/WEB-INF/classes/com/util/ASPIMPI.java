package com.util;

import com.domain.MarketStatus;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/25/11
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ASPIMPI {


    private static Logger log = Logger.getLogger(ASPIMPI.class);

    public static MarketStatus getMarketStatusData() throws IOException, MalformedURLException {

        MarketStatus marketStatusObj = new MarketStatus();
        ;

        try {

            //       Construct data
            String data = URLEncoder.encode("callCount", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
            data += "&" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("/header.htm", "UTF-8");
            data += "&" + URLEncoder.encode("httpSessionId", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
            data += "&" + URLEncoder.encode("scriptSessionId", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
            data += "&" + URLEncoder.encode("c0-scriptName", "UTF-8") + "=" + URLEncoder.encode("MarketDataJS", "UTF-8");
            data += "&" + URLEncoder.encode("c0-methodName", "UTF-8") + "=" + URLEncoder.encode("getTopLosersToday", "UTF-8");
            data += "&" + URLEncoder.encode("c0-id", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
            data += "&" + URLEncoder.encode("batchId", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8");


            // Send data
            URL url = new URL("http://www.cse.lk/dwr/call/plaincall/MarketDataJS.getTopLosersToday.dwr");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
            conn.setRequestProperty("Keep-Alive", "111");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            conn.setRequestProperty("Referer", "http://www.cse.lk/header.htm");
            conn.setRequestProperty("Content-Length", "203");
            // conn.setRequestProperty("POSTDATA", "callCount=1 page=/header.htm httpSessionId=59446A82FC20F6ABAF5803D7F9950C4F scriptSessionId=B7941FCAA9AC9085E32F217F74779D0298 c0-scriptName=MarketDataJS c0-methodName=getIndicesToday c0-id=0 batchId=2");

            conn.setRequestProperty("Accept", "text/javascript");
            conn.setRequestProperty("Accept-Language", "en-us,en;q=0.5");
            conn.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
           // conn.setRequestProperty("Vary","Accept-Encoding");

            conn.setRequestMethod("POST");


            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            wr.write(data);
            wr.flush();

            System.out.println(conn.getResponseCode());

            // Get the response
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            wr.close();
            reader.close();


        } catch (
                Exception e
                )

        {
            log.debug("Exception Occured ...... " + e.getMessage());
        }

        return marketStatusObj;
    }


    public static void main(String[] args) throws IOException {

        getMarketStatusData();

    }


    /**
     * Reads data from the data reader and posts it to a server via POST request.
     * data - The data you want to send
     * endpoint - The server's address
     * output - writes the server's response to output
     *
     * @throws Exception
     */
    public static void postData() throws Exception {

        try {
            // Construct data
            String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
            data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");

            // Send data
            URL url = new URL("http://hostname:80/cgi");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line...
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
        }


    }

    /**
     * Pipes everything from the reader to the writer via a buffer
     */
    private static void pipe(Reader reader, Writer writer) throws IOException {

        char[] buf = new char[1024];
        int read = 0;
        while ((read = reader.read(buf)) >= 0) {
            writer.write(buf, 0, read);
        }
        writer.flush();
    }


}
