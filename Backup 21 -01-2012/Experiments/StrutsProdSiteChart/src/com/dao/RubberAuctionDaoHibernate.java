package com.dao;

import com.domain.RubberPrice;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/25/11
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class RubberAuctionDaoHibernate extends HibernateDaoSupport implements RubberAuctionDao {

    private static Logger log = Logger.getLogger(RubberAuctionDaoHibernate.class);

    public void save(RubberPrice rubberAuctionPrice) {
        log.info(" Save Rubber Data " + rubberAuctionPrice.getDate());
        getHibernateTemplate().save(rubberAuctionPrice);
    }

    public List<RubberPrice> getRubberAuctionPrices() {
        Query query = getSession().getNamedQuery("getAllRubberAuctionPrices");
        return query.list();
    }
}
