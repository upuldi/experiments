package com.site.action.chart;

import com.opensymphony.xwork2.ActionSupport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/28/11
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RealtimeIndexChartAction extends ActionSupport {


    private String date;
    private String aspiparam;
    private String mpiparam;

    public String getDate() {
        return date;
    }
    public String getAspiparam() {
        return aspiparam;
    }

    public String getMpiparam() {
        return mpiparam;
    }

    public String getRealTimeIndexData() {

        Date searchDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyyMMdd");
        date = simpleDateFormatServer.format(searchDate);
        aspiparam = "ASPI|"+date;
        mpiparam = "MPI|"+date;


        return SUCCESS;
    }

}
