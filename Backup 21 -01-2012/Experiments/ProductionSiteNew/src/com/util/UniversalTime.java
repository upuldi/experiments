package com.util;

import com.framework.SpringContext;
import org.apache.log4j.Logger;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GregorianChronology;

import java.util.Date;
import java.util.Properties;


public class UniversalTime {

    private static Logger log = Logger.getLogger(UniversalTime.class);
    private static final String TIME_URL = "http://www.timeanddate.com/worldclock/fullscreen.html?n=389";


    public UniversalTime() {
		super();
	}

	public static DateTime getTime()  {

//
///*        Properties properties = (Properties) SpringContext.getBean("appProperties");
//        String timeUrl = properties.getProperty("web.universaltime.url");
//
//        log.debug("URL : " + timeUrl ); */
//
//		URL cseStockOverview = new URL(TIME_URL);
//		URLConnection yc = cseStockOverview.openConnection();
//		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
//		String inputLine;
//		String fileString = "";
//		int lineNo=0;
//
//		Date dateObj = null;
//
//		while ((inputLine = in.readLine()) != null) {
//
//		//	log.debug(" lineNo " + lineNo + inputLine);
//
//			if(lineNo == 58) {
//
//				log.debug(" LINE : " + inputLine);
//
//
//				String [] dateStr = inputLine.split("<div id=i_time>");
//
//                log.debug("DATE STR : " + dateStr[1]);
//				String timeStr = dateStr[1].substring(0, 11);
//				log.debug(" TIME STR : " + timeStr);
//
//				String DATE_FORMAT = "h:mm:ss a";
//			    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//
//			    Calendar calendar = Calendar.getInstance();
//			    dateObj = sdf.parse(timeStr);
//
//			    dateObj.setDate(calendar.getTime().getDate());
//			    dateObj.setYear(calendar.getTime().getYear());
//			    dateObj.setMonth(calendar.getTime().getMonth());
//
//			    log.debug("DATE OBJ : " + dateObj);
//
//
//			}
//			lineNo++;
//		}
//		in.close();

        /* JODA Code */

        DateTimeZone zoneColombo = DateTimeZone.forID("Asia/Colombo");
        Chronology chronology =  GregorianChronology.getInstance(zoneColombo);
        DateTime dateTime = new DateTime(chronology);
        log.debug("Time Now " + dateTime.toString());

		return dateTime;
	}


    public static Date getLocalTime() {

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String timeZoneSl = properties.getProperty("timezone.cse.custom");

        Date nowServerTime = new Date();
        Date slTimeZoneDate = Timezone.getDateInTimeZone(nowServerTime,timeZoneSl);
        return slTimeZoneDate;
    }

    public static Date getLocalTime(Date date) {

        Properties properties = (Properties) SpringContext.getBean("appProperties");
        String timeZoneSl = properties.getProperty("timezone.cse.custom");

        Date slTimeZoneDate = Timezone.getDateInTimeZone(date,timeZoneSl);
        return slTimeZoneDate;
    }


}
