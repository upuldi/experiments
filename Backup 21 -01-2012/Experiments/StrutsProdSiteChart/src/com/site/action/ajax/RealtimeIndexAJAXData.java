package com.site.action.ajax;

import com.domain.RTIndexValues;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 12/5/11
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class RealtimeIndexAJAXData extends ActionSupport implements ModelDriven {

    private static Logger log = Logger.getLogger(RealtimeIndexAJAXData.class);

    private RTIndexValues rtIndexValues;


    public String getRTIndexData() {

        try {

            String data = URLEncoder.encode("callCount", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
            data += "&" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("/header.htm", "UTF-8");
            data += "&" + URLEncoder.encode("httpSessionId", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
            data += "&" + URLEncoder.encode("scriptSessionId", "UTF-8") + "=" + URLEncoder.encode("26E1945B4904AB03366074875066448D373", "UTF-8");
            data += "&" + URLEncoder.encode("c0-scriptName", "UTF-8") + "=" + URLEncoder.encode("MarketDataJS", "UTF-8");
            data += "&" + URLEncoder.encode("c0-methodName", "UTF-8") + "=" + URLEncoder.encode("getIndicesToday", "UTF-8");
            data += "&" + URLEncoder.encode("c0-id", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
            data += "&" + URLEncoder.encode("batchId", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");

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

            String completeResponseString = completeResponse.toString();

            String[] springArray = completeResponseString.split("[;]");

            int count = 0;
            int countASPI = 0;
            int countMPI = 0;
            String aspiValueContainingString = null;
            String mpiValueContainingString = null;
            String aspiChangeContainingString = null;
            String aspiPresentageContainingString = null;
            String aspiValue = null;
            String aspiChange = null;
            String aspiChangePresentage = null;
            String aspiBlock = null;

            String mpiValue = null;
            String mpiChange = null;
            String mpiChangePresentage = null;
            String mpiBlock = null;
            String mpiChangeContainingString = null;
            String miPresentageContainingString = null;

            for (String s : springArray) {

                log.debug(count + " : " + s);

                if (s.contains("s2.v")) {
                    log.debug("MPI FOUND : " + s);

                }
                if ((s.contains(".id")) && (s.substring(s.indexOf("."), s.length()).equalsIgnoreCase(".id=1"))) {
                    log.debug("ASPI BLOCK FOUND : " + s);
                    countASPI = count;
                    aspiBlock = s.substring(0, s.indexOf("."));
                    aspiValueContainingString = s.substring(0, s.indexOf(".")) + ".v=";
                    aspiChangeContainingString = s.substring(0, s.indexOf(".")) + ".c=";
                    aspiPresentageContainingString = s.substring(0, s.indexOf(".")) + ".p=";
                }
                if (null != aspiValueContainingString && s.contains(aspiValueContainingString)) {
                    log.debug("ASPI STRING FOUND : " + s);
                    aspiValue = s.substring(s.indexOf("=") + 1, s.length());
                    log.debug("ASPI VALUE : " + aspiValue);
                }
                if (null != aspiPresentageContainingString && s.contains(aspiPresentageContainingString)) {
                    log.debug("ASPI CHANGE PRESENTAGE STRING FOUND : " + s);
                    aspiChangePresentage = s.substring(s.indexOf("=") + 1, s.length());
                    log.debug("ASPI CHANGE PRESENTAGE : " + aspiChangePresentage);
                }


                if ((s.contains(".id")) && (s.substring(s.indexOf("."), s.length()).equalsIgnoreCase(".id=41"))) {
                    countMPI = count;
                    log.debug("MPI BLOCK FOUND : " + s);
                    mpiBlock = s.substring(0, s.indexOf("."));
                    mpiValueContainingString = s.substring(0, s.indexOf(".")) + ".v=";
                    mpiChangeContainingString = s.substring(0, s.indexOf(".")) + ".c=";
                    miPresentageContainingString = s.substring(0, s.indexOf(".")) + ".p=";
                }
                if (null != mpiValueContainingString && s.contains(mpiValueContainingString)) {
                    log.debug("MPI STRING FOUND : " + s);
                    mpiValue = s.substring(s.indexOf("=") + 1, s.length());
                    log.debug("MPI VALUE : " + mpiValue);
                }
                if (null != miPresentageContainingString && s.contains(miPresentageContainingString)) {
                    log.debug("MPI CHANGE PRESENTAGE STRING FOUND : " + s);
                    mpiChangePresentage = s.substring(s.indexOf("=") + 1, s.length());
                    log.debug("MPI CHANGE PRESENTAGE : " + mpiChangePresentage);
                }


                count = count + 1;
            }

            log.debug("ASPI VALUE : " + aspiValue);
            log.debug("MPI VALUE : " + mpiValue);

            for (int i = countASPI; i >= countASPI - 5; i--) {

                String s = springArray[i];

                if (null != aspiChangeContainingString && s.contains(aspiChangeContainingString)) {
                    log.debug("ASPI CHANGE STRING FOUND : " + s);
                    aspiChange = s.substring(s.indexOf("=") + 1, s.length());
                    log.debug("ASPI CHANGE : " + aspiChange);
                }
            }
            for (int i = countMPI; i >= countMPI - 5; i--) {

                String s = springArray[i];

                if (null != mpiChangeContainingString && s.contains(mpiChangeContainingString)) {
                    log.debug("MPI CHANGE STRING FOUND : " + s);
                    mpiChange = s.substring(s.indexOf("=") + 1, s.length());
                    log.debug("MPI CHANGE : " + mpiChange);
                }
            }

            DecimalFormat df = new DecimalFormat("#.##");

            DecimalFormat pattern = new DecimalFormat("##0.00;-##0.00");
            DecimalFormat decimalFormatTwo = new DecimalFormat("###.##");


            rtIndexValues = new RTIndexValues();
            rtIndexValues.setASPI(aspiValue);
            rtIndexValues.setMPI(mpiValue);

            double aspiChangeDoubleValue = pattern.parse(aspiChange).doubleValue();
            String aspiChangeClass = null;

            if(aspiChangeDoubleValue >= 0) {
                aspiChangeClass = "cusfont-green";
                rtIndexValues.setAspiImage("green-up.png");
            } else {
                aspiChangeClass = "cusfont-red";
                rtIndexValues.setAspiImage("red-down.png");
            }


            rtIndexValues.setAspiChange(decimalFormatTwo.format(aspiChangeDoubleValue));
            rtIndexValues.setAspiChangeClass(aspiChangeClass);
            rtIndexValues.setAspiChangePresentage(decimalFormatTwo.format(pattern.parse(aspiChangePresentage)));


            double mpiChangeDoubleValue = pattern.parse(mpiChange).doubleValue();
            String mpiChangeClass = null;

            if (mpiChangeDoubleValue  >= 0)  {
                mpiChangeClass = "cusfont-green";
                rtIndexValues.setMpiImage("green-up.png");
            } else {
                mpiChangeClass = "cusfont-red";
                rtIndexValues.setMpiImage("red-down.png");
            }

            rtIndexValues.setMpiChange(decimalFormatTwo.format(mpiChangeDoubleValue));
            rtIndexValues.setMpiChangeClass(mpiChangeClass);
            rtIndexValues.setMpiChangePresentage(decimalFormatTwo.format(pattern.parse(mpiChangePresentage)));



        } catch (Exception e) {
            log.error("Exception Occured ...... " + e.getMessage());
        }

        return SUCCESS;
    }


    public Object getModel() {
        return rtIndexValues;
    }
}

