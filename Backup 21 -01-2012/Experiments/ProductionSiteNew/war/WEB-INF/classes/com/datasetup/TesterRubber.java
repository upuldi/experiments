package com.datasetup;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/25/11
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class TesterRubber {

    public static void main(String []args) throws IOException {

        System.out.println("Starting....");

        String auctionURL = "http://www.crtasl.org/prices.php?auction_date=2012-08-03";

        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage(auctionURL);

        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();

        int i =0;

        while (iteratorHtmlElement.hasNext()) {


            HtmlElement currentElement = iteratorHtmlElement.next();


            if (currentElement instanceof HtmlTable) {

                 System.out.println("***************** TABLE FOUND....");

                if (i==1) {

                    System.out.println("GUSS TABLE FOUND....");

                    HtmlTable htmlTable = (HtmlTable) currentElement;
                    List<HtmlTableRow> rowList = htmlTable.getRows();

                    int length = rowList.size();

                    for (int j=0;j<length-1;j++) {

                        if (j>0) {
                            System.out.println(rowList.get(j).getCell(0).getTextContent() +
                                    " : " + rowList.get(j).getCell(1).getTextContent()+ " : " + rowList.get(j).getCell(2).getTextContent());
                        }
                    }

                }else {
                    i =+ 1;
                }
            }
        }

        System.out.println("DONE.....");




    }
}
