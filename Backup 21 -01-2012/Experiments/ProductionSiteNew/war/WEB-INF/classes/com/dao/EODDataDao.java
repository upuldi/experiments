package com.dao;

import com.domain.CSEListedCompany;
import com.domain.EODData;
import com.domain.TradeProcess;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EODDataDao {


    void saveEODData(EODData eodData);

    List<EODData> getTradedCountersForTheDay(String date);

    List<TradeProcess> getNextNotProcessedTradeEntriesForTheDay(String date);

    long getProcessedTradeCountForTheDay(String date);

    long getTradedCountersCountForTheDay(String date);

    void saveTradeProcessList(List<TradeProcess> tradeProcessListToAdd);

    void updateTradeProcess(TradeProcess tradeProcess);

    void saveCSEListedCompany(CSEListedCompany cseListedCompany);
}
