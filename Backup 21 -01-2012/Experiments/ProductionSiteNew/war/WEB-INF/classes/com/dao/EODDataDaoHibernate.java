package com.dao;

import com.domain.CSEListedCompany;
import com.domain.EODData;
import com.domain.TradeProcess;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODDataDaoHibernate extends HibernateDaoSupport implements EODDataDao {

    public void saveEODData(EODData eodData) {
        getHibernateTemplate().save(eodData);
    }

    public List<EODData> getTradedCountersForTheDay(String date) {

        Query query = getSession().getNamedQuery("getEODCountersForTheDay");
        query.setString("date", date);
        return query.list();
    }

    public List<TradeProcess> getNextNotProcessedTradeEntriesForTheDay(String date) {

        Query query = getSession().getNamedQuery("getNextNotProcessedTradeEntriesForTheDay");
        query.setString("date", date);
        query.setFirstResult(0);
        query.setMaxResults(10);
        return query.list();
    }

    public long getProcessedTradeCountForTheDay(String date) {

        long processedTradeCount = 0;

        String strQuery = "SELECT count(*) COUNT FROM `upuldi_CSEDAILYDATA`.`TRADE_PROCESS` where date = :date";
        SQLQuery sqlQuery = getSession().createSQLQuery(strQuery);
        sqlQuery.setString("date",date);
        sqlQuery.addScalar("COUNT", Hibernate.LONG);

        Object x = sqlQuery.uniqueResult();
        Long longVal = (Long) x;
        processedTradeCount = longVal.longValue();

        return processedTradeCount;
    }

    public long getTradedCountersCountForTheDay(String date) {

        long tradedTradeCountersCount = 0;

        String strQuery = "SELECT count(*) COUNT FROM `upuldi_CSEDAILYDATA`.`EOD_DATA` where date = :date";
        SQLQuery sqlQuery = getSession().createSQLQuery(strQuery);
        sqlQuery.setString("date",date);
        sqlQuery.addScalar("COUNT", Hibernate.LONG);

        Object x = sqlQuery.uniqueResult();
        Long longVal = (Long) x;
        tradedTradeCountersCount = longVal.longValue();

        return tradedTradeCountersCount;
    }

    public void saveTradeProcessList(List<TradeProcess> tradeProcessListToAdd) {
        getHibernateTemplate().saveOrUpdateAll(tradeProcessListToAdd);
    }

    public void updateTradeProcess(TradeProcess tradeProcess) {
        getHibernateTemplate().saveOrUpdate(tradeProcess);
    }

    public void saveCSEListedCompany(CSEListedCompany cseListedCompany) {
        getHibernateTemplate().save(cseListedCompany);
    }
}
