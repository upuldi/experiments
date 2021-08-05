package com.other;

import com.asprise.util.pdf.PDFReader;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/22/11
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Aspire {

    public static void main(String[] args) throws IOException {

        System.out.println("Starting");
        File f = new File("/home/udoluweera/Desktop/cse/weekly_report.pdf");
        PDFReader reader = new PDFReader(f);
        reader.open(); // open the file.
        int pages = reader.getNumberOfPages();

        for (int i = 0; i < 2; i++) {
            String text = reader.extractTextFromPage(i);
            System.out.println("Page " + i + ": " + text);
        }

        // perform other operations on pages.

        reader.close(); // finally, close the file.


    }
}
