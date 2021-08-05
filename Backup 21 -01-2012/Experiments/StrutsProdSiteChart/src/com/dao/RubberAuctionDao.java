package com.dao;

import com.domain.RubberPrice;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/25/11
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RubberAuctionDao {


    void save(RubberPrice rubberAuctioPrice);

    List<RubberPrice> getRubberAuctionPrices();
}
