package com.site.action.link;

import com.opensymphony.xwork2.ActionSupport;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GregorianChronology;

import java.util.Calendar;
import java.util.TimeZone;

public class LinkAction extends ActionSupport {

    private String selectedLink;
    private String timeNow;
    private String localTimeZone;
    private String timeColombo;

    public String getLocalTimeZone() {
        return localTimeZone;
    }

    public String getTimeNow() {
        return timeNow;
    }

    public String getTimeColombo() {
        return timeColombo;
    }

    private static final long serialVersionUID = -2613425890762568273L;

	public String home()
	{
        selectedLink = "home";
		return "home";
	}
	
	public String statictext()
	{
        selectedLink = "examples";
		return "statictext";
	}
	
	public String examples()
	{
        selectedLink = "examples";
		return "examples";
	}

	public String commodity()
	{
        selectedLink = "commodity";
		return "commodity";
	}

	public String gold()
	{
        selectedLink = "commodity";
		return "gold";
	}



	public String realtime()
	{
        selectedLink = "realtime";
		return "realtime";
	}

	public String defaultStock()
	{
        selectedLink = "charts";
		return "defaultStock";
	}

	public String trade()
	{

        Calendar calender = Calendar.getInstance();

        timeNow = calender.getTime().toString();
        localTimeZone = Calendar.getInstance().getTimeZone().getDisplayName();

      //  TimeZone timeZoneColombo = TimeZone.getTimeZone("Asia/Colombo");
        TimeZone timeZoneColombo = TimeZone.getTimeZone("Pacific/Apia");
        calender.setTimeZone(timeZoneColombo);

        timeColombo = calender.getTime().toString();


        DateTimeZone zoneColombo = DateTimeZone.forID("Asia/Colombo");
        Chronology chronology =  GregorianChronology.getInstance(zoneColombo);
        DateTime dt2 = new DateTime(chronology);
        timeColombo = dt2.toString();


        selectedLink = "charts";
		return "trade";
	}

	public String contactUs()
	{
        selectedLink = "contacts";
		return "contactUs";
	}

	public String foriegnHolding()
	{
        selectedLink = "charts";
		return "foriegnHolding";
	}





    public String getSelectedLink() {
        return selectedLink;
    }

    public void setSelectedLink(String selectedLink) {
        this.selectedLink = selectedLink;
    }
}
