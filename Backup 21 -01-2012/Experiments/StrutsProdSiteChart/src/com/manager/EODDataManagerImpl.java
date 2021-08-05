package com.manager;

import com.dao.EODDataDao;
import com.domain.EODData;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 10/31/11
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODDataManagerImpl implements EODDataManager {

    private EODDataDao eodDataDao;

    public void setEodDataDao(EODDataDao eodDataDao) {
        this.eodDataDao = eodDataDao;
    }

    public EODData getEODDataForStock(String dateString, String stockCode) {
        return eodDataDao.getEODDataForStock(dateString,stockCode);
    }
}
