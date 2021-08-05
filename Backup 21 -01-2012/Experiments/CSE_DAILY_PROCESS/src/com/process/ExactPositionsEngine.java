package com.process;

import com.domain.Span;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/6/11
 * Time: 10:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExactPositionsEngine {

    private static Logger log = Logger.getLogger(ExactPositionsEngine.class);
     static Properties properties = new Properties();

    private static void init() throws IOException {
        properties.load(new FileInputStream("/home/udoluweera/Experiments/CSE_DAILY_PROCESS/src/process.properties"));
    }



    public static void main(String[] args) throws IOException {

        log.debug("Starting.....");

        init();

        String processFileName = properties.getProperty("process.html.file");

        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("file:///" + processFileName);


        Iterator<HtmlElement> iteratorHtmlElement = page.getAllHtmlChildElements().iterator();

        Map<Integer, HtmlDivision> divCountMap = new HashMap<Integer, HtmlDivision>();
        Map<HtmlDivision, Integer> divContentMap = new HashMap<HtmlDivision, Integer>();

        Map<Integer, List<Span>> spanTopMapRow = new TreeMap<Integer, List<Span>>();


        int divCount = 1;
        while (iteratorHtmlElement.hasNext()) {

            HtmlElement currentElement = iteratorHtmlElement.next();


            if (currentElement instanceof HtmlSpan) {

                HtmlSpan htmlSpan = (HtmlSpan) currentElement;

                String arributeString = htmlSpan.getAttribute("style");
                log.debug(arributeString);

                String[] attributeSectionsArray = arributeString.split("[;]");

                String leftString = "";
                String topString = "";
                String fontSizeString = "";
                String widthSizeString = "";
                String heightString = "";

                String leftStringValueOnly = "";
                String topStringValueOnly = "";
                String fontSizeStringValueOnly = "";
                String widthSizeStringValueOnly = "";
                String heightStringValueOnly = "";


                for (String arrayElement : attributeSectionsArray) {

                    if (arrayElement.contains("left")) {
                        leftString = arrayElement;
                        if (-1 != leftString.indexOf(":") && -1 != leftString.indexOf("p")) {
                            leftStringValueOnly = leftString.substring(leftString.indexOf(":") + 1, leftString.indexOf("p"));
                        }
                    }
                    if (arrayElement.contains("top")) {
                        topString = arrayElement;
                        if (-1 != topString.indexOf(":") && -1 != topString.indexOf("p")) {
                            topString = topString.substring(topString.indexOf(":") + 1, topString.length());
                            topStringValueOnly = topString.substring(0, topString.indexOf("p"));
                        }
                    }
                    if (arrayElement.contains("font-size")) {
                        fontSizeString = arrayElement;
                        if (-1 != fontSizeString.indexOf(":") && -1 != fontSizeString.indexOf("p")) {
                            fontSizeStringValueOnly = fontSizeString.substring(fontSizeString.indexOf(":") + 1, fontSizeString.indexOf("p"));
                        }
                    }
                    if (arrayElement.contains("width")) {
                        widthSizeString = arrayElement;
                        if (-1 != widthSizeString.indexOf(":") && -1 != widthSizeString.indexOf("p")) {
                            widthSizeStringValueOnly = widthSizeString.substring(widthSizeString.indexOf(":") + 1, widthSizeString.indexOf("p"));
                        }
                    }
                    if (arrayElement.contains("height")) {
                        heightString = arrayElement;
                        if (-1 != heightString.indexOf(":") && -1 != heightString.indexOf("p")) {
                            heightStringValueOnly = heightString.substring(heightString.indexOf(":") + 1, heightString.indexOf("p"));
                        }
                    }
                }

                log.debug("leftString " + leftString);
                log.debug("topString " + topString);
                log.debug("fontSizeString " + fontSizeString);
                log.debug("heightString " + heightString);

                log.debug("leftStringValueOnly  " + leftStringValueOnly);
                log.debug("topStringValueOnly " + topStringValueOnly);
                log.debug("fontSizeStringValueOnly " + fontSizeStringValueOnly);
                log.debug("heightStringValueOnly " + heightStringValueOnly);

                Span span = new Span();
                span.setHeight(!heightStringValueOnly.trim().equalsIgnoreCase("") ? Integer.parseInt(heightStringValueOnly) : 0);
                span.setLeft(!leftStringValueOnly.trim().equalsIgnoreCase("") ? Integer.parseInt(leftStringValueOnly) : 0);
                span.setTextContent(htmlSpan.asText());
                span.setTop(!topStringValueOnly.trim().equalsIgnoreCase("") ? Integer.parseInt(topStringValueOnly) : 0);
                span.setFontSize(!fontSizeStringValueOnly.trim().equalsIgnoreCase("") ? Integer.parseInt(fontSizeStringValueOnly) : 0);
                span.setWidthSize(!widthSizeStringValueOnly.trim().equalsIgnoreCase("") ? Integer.parseInt(widthSizeStringValueOnly) : 0);

                if (null != spanTopMapRow.get(span.getTop())) {

                    List<Span> rowcontent = spanTopMapRow.get(span.getTop());
                    rowcontent.add(span);

                } else {

                    List<Span> rowcontent = new ArrayList<Span>();
                    rowcontent.add(span);
                    spanTopMapRow.put(span.getTop(), rowcontent);
                }
            }
        }
        log.debug("Completed...");

        //Set<Map.Entry<String,List<Span>>> entry = spanTopMapRow.entrySet();

        String step1File = properties.getProperty("process.step1.file");
        FileWriter fstream = new FileWriter(step1File);
        BufferedWriter out = new BufferedWriter(fstream);

        for (Integer key : spanTopMapRow.keySet()) {

            List<Span> spanList = spanTopMapRow.get(key);
            Collections.sort(spanList);

            String rowContent = "";
            boolean previousEmpty = false;
            boolean previousEmptyMoreThanOnce = false;
            String previousValue = null;

            List<Span> formattedSpanList = new ArrayList<Span>();

            for (Span span : spanList) {

                String currentValue = span.getTextContent();

                if (null == previousValue) {

                    previousValue = currentValue;
                }

                if (currentValue.equalsIgnoreCase("")) {

                    if (previousEmpty == true) {
                        previousEmptyMoreThanOnce = true;
                    } else {
                        previousEmptyMoreThanOnce = false;
                    }

                    previousEmpty = true;

                } else {

                    if (previousEmptyMoreThanOnce == true || (previousEmptyMoreThanOnce == false && previousEmpty == true)) {
                        rowContent = rowContent + "\t" + currentValue;
                    } else {
                        rowContent = rowContent + currentValue;
                    }
                    previousValue = currentValue;
                    previousEmpty = false;
                    previousEmptyMoreThanOnce = false;

                }
            }
            log.debug("ROW CONTENT IS :: " + rowContent);
            boolean validation = validateRow(rowContent);
            if (validation) {
                String formatContent = formatRow(rowContent);
                out.write(formatContent + "\n");
            }
        }
        out.close();

    }

    private static String formatRow(String rowContent) {

        char[] charArrayFormattedRow = rowContent.toCharArray();

        int firstChar = 0;
        boolean escapeBlook = false;

        for (int i = 0;i<charArrayFormattedRow.length;i++) {

            char checkingChar = charArrayFormattedRow[i];
            /* Check for Other text formats [] */
            if (checkingChar == '[') {
                escapeBlook = true;
            }

            if (checkingChar == ']') {
                escapeBlook = false;
            }


            if (!escapeBlook) {
                if (Character.isDigit(checkingChar)) {
                    firstChar = i;
                    break;
                }
            }
        }

        String firstPart = rowContent.substring(0, firstChar);
        String rest = rowContent.substring(firstChar, rowContent.length());

        log.debug("First Part is : " + firstPart);
        log.debug("REST is : " + rest);

        char[] charArrayFirstPart = firstPart.toCharArray();
        String formattedFirstPart = "";

        for (int i = 0;i<charArrayFirstPart.length;i++) {

            char checkingChar = charArrayFormattedRow[i];

            if (checkingChar != '\t' && (!Character.isSpaceChar(checkingChar))) {

                formattedFirstPart = formattedFirstPart+checkingChar;
            }
        }

        log.debug("Formatted First Part is : " + formattedFirstPart);

        String fullyFormattedStr = "";

        if (!formattedFirstPart.isEmpty()) {
            fullyFormattedStr = formattedFirstPart + "\t" + rest;
        } else {
            fullyFormattedStr = rest;
        }

        log.debug("Fully Formatted Str : " + fullyFormattedStr);
        
        return fullyFormattedStr;
    }


    private static List<String> getTickerBoardList() {

        List<String> boardList = new ArrayList<String>();

        String[] boardsArray = {"MAINBOARD", "DIRISAVIBOARD", "DEFAULTBOARD"};

        for (String board : boardsArray) {
            boardList.add(board);
        }

        return boardList;
    }

    private static List<String> getTickerSectorsList() {

        List<String> sectorsList = new ArrayList<String>();

        String[] sectorsArray = {"BANKSFINANCEANDINSURANCE", "BEVERAGEFOODANDTOBACCO", "CHEMICALSANDPHARMACEUTICALS",
                "CLOSEDENDFUNDS", "CONSTRUCTIONANDENGINEERING", "DIVERSIFIEDHOLDINGS", "FOOTWEARANDTEXTILES", "HEALTHCARE", "HOTELSANDTRAVELS",
                "INFORMATIONTECHNOLOGY", "INVESTMENTTRUSTS", "LANDANDPROPERTY", "MANUFACTURING", "MOTORS", "OILPALMS", "PLANTATIONS",
                "POWERANDENERGY", "SERVICES", "STORESANDSUPPLIES", "TELECOMMUNICATIONS", "TRADING"};

        for (String sector : sectorsArray) {
            sectorsList.add(sector);
        }

        return sectorsList;
    }

    private static boolean validateRow(String rowContent) {

        boolean isValidated = true;

        String formatedRow = rowContent.replace("\t", "");

        log.debug("Formatted Row : " + formatedRow);

        if (getTickerBoardList().contains(formatedRow)) {
            isValidated = false;
        }
        if (getTickerSectorsList().contains(formatedRow)) {
            isValidated = false;
        }
        if (getTickerGarbageList().contains(formatedRow)) {
            isValidated = false;
        }
        return isValidated;
    }

    private static List<String> getTickerGarbageList() {

        List<String> garbageList = new ArrayList<String>();

        String[] garbageArray = {"(+)"};

        for (String board : garbageArray) {
            garbageList.add(board);
        }

        return garbageList;
    }

}
