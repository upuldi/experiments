package com.test;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/6/11
 * Time: 9:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tester {

    public static void main (String []args){

        String formatedRow = "A.SPEN.HOT.HOLD.\t76.00\t75.5025/08/11\t76.00\t75.50\t2,269,527\t336,290,010\t562050\t25,558,040,760\t107,859,409";
        System.out.println("Value is : " + formatedRow.indexOf(":"));


      //  String formatedRow = x.replace(" \t ", "");
        char[] charArrayFormattedRow = formatedRow.toCharArray();

        int firstChar = 0;

        for (int i = 0;i<charArrayFormattedRow.length;i++) {

            char checkingChar = charArrayFormattedRow[i];
            if (Character.isDigit(checkingChar)) {
                firstChar = i;
                break;
            }
        }

        String firstPart = formatedRow.substring(0, firstChar);
        String rest = formatedRow.substring(firstChar, formatedRow.length());

        System.out.println("First Part is : " + firstPart);
        System.out.println("REST is : " + rest);

        char[] charArrayFirstPart = firstPart.toCharArray();
        String formattedFirstPart = "";

        for (int i = 0;i<charArrayFirstPart.length;i++) {

            char checkingChar = charArrayFormattedRow[i];

            if (checkingChar != '\t' && (!Character.isSpaceChar(checkingChar))) {

                formattedFirstPart = formattedFirstPart+checkingChar;
            }
        }

        System.out.println("Formatted First Part is : " + formattedFirstPart);

        String fullyFormattedStr = formattedFirstPart + "\t" + rest;

        System.out.println("Fully Formatted Str : " + fullyFormattedStr);


        String ss = "CEYLON\t14.30\t14.2025/08/11\t15.00\t14.20\t219,700\t25,000,000\t398000\t24,987,924";










    }

}
