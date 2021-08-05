package com.util;

import com.domain.MarketStatus;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/28/11
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tester {

    public static void main(String []args) throws IOException {

        System.out.println("************************");

        MarketStatus ms = CSEData.getMarketStatusData();

    }
}
