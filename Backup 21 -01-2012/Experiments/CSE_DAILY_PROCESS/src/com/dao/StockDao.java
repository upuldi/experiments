package com.dao;

import com.db.CommonHibernateSession;
import com.domain.Stock;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/13/11
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockDao extends CommonHibernateSession {

    private static Logger log = Logger.getLogger(StockDao.class);

    public void saveStock(Stock stock) {

        log.debug(" Persisting Stock  :  " + stock.getTickerString());

        Session session = getSession();
        session.save(stock);
    }

}
