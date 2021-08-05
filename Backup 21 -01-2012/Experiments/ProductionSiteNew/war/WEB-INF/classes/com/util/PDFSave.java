package com.util;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/26/11
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class PDFSave {

     private static Logger log = Logger.getLogger(ASPIMPI.class);

    public static void main(String[] args) throws IOException {

        System.out.println("Starting.....");

        String url_daily = "http://www.cse.lk/cmt/upload_cse_report_file/daily_report.pdf";
        savePDF(url_daily,"/dist/");

        String url_weekly = "http://www.cse.lk/cmt/upload_cse_report_file/weekly_report.pdf";
        savePDF(url_weekly,"/dist/");
    }

    public static void savePDF(String spec,String location) throws IOException {

        log.debug("Start Saving PDF " + spec);

        URL url = new URL(spec);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file.");
        }
        InputStream raw = uc.getInputStream();
        InputStream in = new BufferedInputStream(raw);
        byte[] data = new byte[contentLength];
        int bytesRead = 0;
        int offset = 0;
        while (offset < contentLength) {
            bytesRead = in.read(data, offset, data.length - offset);
            if (bytesRead == -1)
                break;
            offset += bytesRead;
        }
        in.close();

        if (offset != contentLength) {
            throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
        }

        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(now);


        String filename = dateString + "_" + url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
        FileOutputStream out = new FileOutputStream(location+filename);
        out.write(data);
        out.flush();
        out.close();

        log.debug("PDF " + location+filename + " saved Successfully");
    }

}
