package com.dao;

import com.domain.EODData;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EODDataDao {


    void saveEODData(EODData eodData);

    EODData getEODDataForStock(String dateString, String stockCode);
}
