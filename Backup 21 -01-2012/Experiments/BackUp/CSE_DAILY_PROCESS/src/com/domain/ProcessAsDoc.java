package com.domain;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/31/11
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProcessAsDoc {

    private static Logger log = Logger.getLogger(ProcessAsDoc.class);


     public static void main(String[] args) throws IOException {

         log.debug("Starting.....");

         final WebClient webClient = new WebClient();
         final HtmlPage page = webClient.getPage("file:///home/udoluweera/Experiments/CSE_DAILY_PROCESS/input/page55-60.html");


         Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();

         Map<Integer,HtmlDivision> divCountMap = new HashMap<Integer, HtmlDivision>();
         Map<HtmlDivision,Integer> divContentMap = new HashMap<HtmlDivision, Integer>();


         int divCount = 1;
         while (iteratorHtmlElement.hasNext()) {

             HtmlElement currentElement = iteratorHtmlElement.next();


             if (currentElement instanceof HtmlDivision) {

                 HtmlDivision htmlDivision = (HtmlDivision) currentElement;

                 if (264 == divCount ) {

                     log.debug("DIV " + divCount +" CONTENT " + htmlDivision.getTextContent());
                     log.debug(" NEXT " + htmlDivision.getNextSibling().asText());

                 }

                 divCountMap.put(divCount,htmlDivision);
                 divContentMap.put(htmlDivision,divCount);
                 divCount = divCount +1;

             }
         }

         log.debug("MAP SIZE " + divCountMap.size());


         HtmlDivision startingDiv = divCountMap.get(202);
         HtmlDivision endingDiv = divCountMap.get(341);

         HtmlDivision processingDiv = startingDiv;

         while (!processingDiv.equals(endingDiv)) {

             log.debug(" CONTENT COUNTS ARE : " + divContentMap.get(processingDiv));
             processingDiv = (HtmlDivision) processingDiv.getNextSibling();
         }

     }
}
