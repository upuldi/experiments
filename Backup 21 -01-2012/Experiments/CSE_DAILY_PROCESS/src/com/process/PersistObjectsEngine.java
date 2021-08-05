package com.process;

import com.dao.StockDao;
import com.domain.Stock;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/13/11
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistObjectsEngine {

    private static Logger log = Logger.getLogger(PersistObjectsEngine.class);
    static Properties properties = new Properties();


    public static void main(String[] args) throws IOException {

        log.debug("Start Persisting Objects in to Database..................");

        init();

        String processFileName = properties.getProperty("process.step2.file");
        log.debug("USing File : " + processFileName);

        Scanner scanner = new Scanner(new FileReader(processFileName));

        List<Stock> processedStockList = new ArrayList<Stock>();

        while (scanner.hasNext()) {

            String currentLine = scanner.nextLine();
            log.debug(currentLine);

            String[] arrtibuteArray = currentLine.split("[:]");

            Stock stock = new Stock();
            stock.setTickerString(removeNullString(arrtibuteArray[0]));
            stock.setVwaPrice(removeNullString(arrtibuteArray[1]));
            stock.setLastTradedPRice(removeNullString(arrtibuteArray[2]));
            stock.setLastTradedDate(removeNullString(arrtibuteArray[3]));
            stock.setHigh(removeNullString(arrtibuteArray[4]));
            stock.setLow(removeNullString(arrtibuteArray[5]));
            stock.setForeginHolding(removeNullString(arrtibuteArray[6]));
            stock.setIssuedQuantity(removeNullString(arrtibuteArray[7]));
            stock.setTurnover(removeNullString(arrtibuteArray[8]));
            stock.setIndexedMarketCap(removeNullString(arrtibuteArray[9]));
            stock.setQtyInCDS(removeNullString(arrtibuteArray[10]));

            log.debug(stock.toString());
            processedStockList.add(stock);

        }

        log.debug("Persisting Objects to the DB.....");
        StockDao stockDao = new StockDao();

        int writeCount = 0;
        for (Stock stock : processedStockList) {

            try {
                stockDao.saveStock(stock);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            removeNthLine(processFileName, writeCount);

            writeCount = writeCount + 1;
        }
    }

    private static String removeNullString(String s) {
        return s.equalsIgnoreCase("null") ? null : s;
    }


    public static void removeNthLine(String f, int toRemove) throws IOException {

        File tmp = File.createTempFile("tmp", "");

        BufferedReader br = new BufferedReader(new FileReader(f));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));

        for (int i = 0; i > toRemove; i++)
            bw.write(String.format("%s%n", br.readLine()));

        br.readLine();

        String l;
        while (null != (l = br.readLine()))
            bw.write(String.format("%s%n", l));

        br.close();
        bw.close();

        File oldFile = new File(f);
        if (oldFile.delete())
            tmp.renameTo(oldFile);

    }


    private static void init() throws IOException {

        properties.load(new FileInputStream("/home/udoluweera/Experiments/CSE_DAILY_PROCESS/src/process.properties"));
    }

}
