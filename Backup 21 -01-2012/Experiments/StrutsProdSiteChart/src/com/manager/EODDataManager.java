package com.manager;

import com.domain.EODData;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 10/31/11
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EODDataManager {

    EODData getEODDataForStock(String dateString, String stockCode);
}
