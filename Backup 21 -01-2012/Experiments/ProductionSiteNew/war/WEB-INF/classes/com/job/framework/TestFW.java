package com.job.framework;

import com.util.UniversalTime;
import org.joda.time.DateTime;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/14/11
 * Time: 12:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestFW {


    public static void main(String[] args) throws IOException, ParseException {

        DateTime dt = UniversalTime.getTime();
        System.out.println("dt" + dt.toString("yyyy/MM/dd hh:mm:ss"));


    }
}
