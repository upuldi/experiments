package com.dao;

import com.domain.GoldPrice;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 1/19/12
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoldPriceDaoHibernate extends HibernateDaoSupport implements GoldPriceDao{


    public void saveGoldPrice(GoldPrice goldPrice) {
        getHibernateTemplate().save(goldPrice);
    }
}
