package com.test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/26/11
 * Time: 3:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainProcess {

    private static Logger log = Logger.getLogger(MainProcess.class);


    public static void main(String[] args) throws IOException {

        log.debug("Starting.....");

        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("file:///home/udoluweera/Experiments/CSE_DAILY_PROCESS/input/page55-60.html");


        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();

        Map<Integer,HtmlDivision> divCountMap = new HashMap<Integer, HtmlDivision>();

        Map<Integer,Boolean> divCountList = new HashMap<Integer, Boolean>();
        divCountList.put(new Integer(358),Boolean.TRUE);
        divCountList.put(new Integer(367),Boolean.TRUE);
        divCountList.put(new Integer(359),Boolean.TRUE);
        divCountList.put(new Integer(362),Boolean.TRUE);
        divCountList.put(new Integer(411),Boolean.TRUE);

        int divCount = 1;
        while (iteratorHtmlElement.hasNext()) {

            HtmlElement currentElement = iteratorHtmlElement.next();


            if (currentElement instanceof HtmlDivision) {

                HtmlDivision htmlDivision = (HtmlDivision) currentElement;

                if (264 == divCount ) {

                    log.debug("DIV " + divCount +" CONTENT " + htmlDivision.getTextContent());
//                    log.debug("START " + htmlDivision.getStartColumnNumber() +" END " + htmlDivision.getEndColumnNumber());
//                    log.debug("START ROW " + htmlDivision.getStartLineNumber() +" END " + htmlDivision.getEndLineNumber());
//                    log.debug(" NEXT " + htmlDivision.getNextSibling().asText());

                }

                if (null != divCountList.get(new Integer(divCount))) {

//                    log.debug("left" +  htmlDivision.getAttributeNode("left") + " top " + htmlDivision.getAttribute("top") +
//                            " width " + htmlDivision.getAttribute("width") + " higth" + htmlDivision.getAttribute("height"));

                    log.debug("START " + htmlDivision.getStartColumnNumber() +" END " + htmlDivision.getEndColumnNumber());
                    log.debug("START ROW " + htmlDivision.getStartLineNumber() +" END " + htmlDivision.getEndLineNumber());
                    log.debug(" NEXT " + htmlDivision.getNextSibling().asText());

                }


                divCountMap.put(divCount,htmlDivision);
                divCount = divCount +1;

            }
        }

        log.debug("MAP SIZE " + divCountMap.size());


//        for (Map.Entry<Integer,HtmlDivision> entry  : divCountMap.entrySet() ) {
//
//            String htmlDivStringVal = entry.getValue().getTextContent();
//            log.debug("DIV CONTENT VAL : " + htmlDivStringVal);
//
//
//            for (String value : htmlDivStringVal.split("[\n]")) {
//
//                log.debug("Content Line Val : " + value );
//
//                if ((value + "\n").trim().contains("30,423,661,884")) {
//
//                    log.debug("************************************************ ");
//                    log.debug("************** DIV COUNT IS : " + entry.getKey() +"  *************");
//                    log.debug("************************************************ ");
//
//                    System.exit(0);
//
//                }
//            }
//        }



    }


}
