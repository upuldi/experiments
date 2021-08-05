package com.datasetup;

import com.domain.GoldPrice;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 1/19/12
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProcessGoldPrice {

    public static void main(String[] args) throws IOException, ParseException {

        System.out.println(" Starting ......................");


        final WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);
        webClient.setAppletEnabled(false);
        webClient.setCssEnabled(false);

        final HtmlPage page = webClient.getPage("http://www.goldpricerate.com/english/gold-price-in-sri_lanka.php");

        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();


        int tableCount = 0;
        while (iteratorHtmlElement.hasNext()) {

            HtmlElement currentElement = iteratorHtmlElement.next();

            if (currentElement instanceof HtmlTable) {


                System.out.println(" Table Found  ......................" + tableCount);

                HtmlTable htmlTable = (HtmlTable) currentElement;

                if (7 == tableCount) {

                    System.out.println(" Table Found  ");
                    List<HtmlTableRow> rowList = htmlTable.getRows();
                    int rowCount = 0;

                    for (HtmlTableRow row : rowList) {

                        if (rowCount == 0) {
                            rowCount = rowCount +1;
                            continue;
                        }

                        String date = row.getCell(0).asText();
                        String goldOunce = row.getCell(1).asText();
                        String goldPound = row.getCell(2).asText();
                        String gramKarat24 = row.getCell(3).asText();
                        String gramKarat22 = row.getCell(4).asText();
                        String gramKarat21 = row.getCell(5).asText();
                        String gramKarat18 = row.getCell(6).asText();
                        String gramKarat14 = row.getCell(7).asText();

                        System.out.println(" Date is " + date);
                        System.out.print(" goldOunce is " + goldOunce);
                        System.out.print(" goldPound is " + goldPound);
                        System.out.print(" gramKarat24 is " + gramKarat24);
                        System.out.print(" gramKarat22 is " + gramKarat22);
                        System.out.print(" gramKarat21 is " + gramKarat21);
                        System.out.print(" gramKarat18 is " + gramKarat18);
                        System.out.print(" gramKarat14 is " + gramKarat14);

                        GoldPrice goldPrice = new GoldPrice();

                        String dateMonth = date.substring(date.length() - 2, date.length());
                        String dateYear = null;

                        if (dateMonth.equalsIgnoreCase("12")) {
                            dateYear = "-2011";
                        }else {
                            dateYear = "-2012";
                        }
                        String dateFullString = date + dateYear;
                        System.out.println(" Full Date String  :" + dateFullString+ ":0");
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        
                        goldPrice.setDate(simpleDateFormat.parse(dateFullString));
                        goldPrice.setGoldOunce(goldOunce);
                        goldPrice.setGoldPound(goldPound);
                        goldPrice.setGramKarat24(gramKarat24);
                        goldPrice.setGramKarat22(gramKarat22);
                        goldPrice.setGramKarat22(gramKarat22);
                        goldPrice.setGramKarat21(gramKarat21);
                        goldPrice.setGramKarat18(gramKarat18);
                        goldPrice.setGramKarat14(gramKarat14);



                        rowCount = rowCount +1;
                    }


                }


                tableCount = tableCount + 1;

            }
        }


    }

}
