package com.manager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/21/11
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class testmainclass {

    public static void main(String []args) throws ParseException {


        String number = "5,634.25";
        NumberFormat usFormat = NumberFormat.getInstance();
        System.out.println(usFormat.parse(number).doubleValue());

        double d1 = 5634.25 - 5641.33;
        System.out.println( d1);


        BigDecimal bd = new BigDecimal(d1).setScale(2, RoundingMode.HALF_EVEN);
        double d2 = bd.doubleValue();
        System.out.println(d2);




    }
}
