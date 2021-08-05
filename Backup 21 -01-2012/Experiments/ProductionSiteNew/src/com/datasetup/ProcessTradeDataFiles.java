package com.datasetup;

import com.dao.StockTradeDao;
import com.domain.StockTrade;
import com.framework.SpringContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 10/10/11
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProcessTradeDataFiles {

    private String DIRECTORY_PATH = "/home/udoluweera/Desktop/trades";

    public void processTradeDataFiles() throws FileNotFoundException {

        File[] files = new File(DIRECTORY_PATH).listFiles();

        for (File file : files) {

            /* do something with this file */
            System.out.println(" FILE IS " + file.getName());

            /* Process only files not directories. */
            if (file.isFile()) {

                List<StockTrade> stockTradeList = new ArrayList<StockTrade>();

                FileReader fin = new FileReader(file.getAbsolutePath());
                Scanner src = new Scanner(fin);
                while (src.hasNext()) {

                    String line = src.nextLine();
                    System.out.println("LINE " + line);

                    StockTrade stockTrade = new StockTrade();

                    String[] lineContent = line.split("[|]");


                    String date = lineContent[0];
                    String ticker = lineContent[1];
                    String time = lineContent[2];
                    double price = Double.parseDouble(lineContent[3]);
                    Long vol = Long.parseLong(lineContent[4]);

                    stockTrade.setDate(date);
                    stockTrade.setTicker(ticker);
                    stockTrade.setTime(time);
                    stockTrade.setPrice(price);
                    stockTrade.setTradeVol(vol);

                    stockTradeList.add(stockTrade);
                }
                saveStockTradeListForFile(stockTradeList);
            }

            file.delete();
        }

    }

    private void saveStockTradeListForFile(List<StockTrade> stockTradeList) {

        StockTradeDao stockTradeDao = (StockTradeDao) SpringContext.getBean("stockTradeDao");
        stockTradeDao.addStockTrades(stockTradeList);
    }
}
