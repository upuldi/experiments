package com.datasetup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/25/11
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateForward {

    public static void main(String[]args) throws ParseException {

        System.out.println(" Starting ......................");


        String dateFormatString = "yyyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatString);

        String dareString = "2010-08-03";
        Date startDateString = simpleDateFormat.parse(dareString);

        Calendar c = Calendar.getInstance();
        c.setTime(startDateString);

        Date startDate =  c.getTime();

        System.out.println(startDate);
        System.out.println((Calendar.getInstance().getTime()).after(startDate));

        for(Date d = c.getTime();(Calendar.getInstance().getTime()).after(d);c.add(Calendar.DATE, 1)){

            d = c.getTime();
            System.out.println(simpleDateFormat.format(c.getTime()));
        }

    }
}
