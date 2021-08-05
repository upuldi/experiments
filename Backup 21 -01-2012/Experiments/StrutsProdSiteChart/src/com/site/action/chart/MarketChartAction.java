package com.site.action.chart;

import com.dao.MarketSummeryViewDao;
import com.domain.DailyMarketSummery;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/2/11
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketChartAction extends ActionSupport {

    private String selectedLink;
    private String aspiChange;
    private String mpiChange;
    private String aspiChangePresentage;
    private String mpiChangePresentage;
    private String aspiValue;
    private String mpiValue;
    private String fontClassASPIChangeValue;
    private String fontClassASPIChangePresentage;
    private String fontClassMPIChangeValue;
    private String fontClassMPIChangePresentage;
    private String aspiImage;
    private String mpiImage;

    private MarketSummeryViewDao marketSummeryViewDao;

    public void setMarketSummeryViewDao(MarketSummeryViewDao marketSummeryViewDao) {
        this.marketSummeryViewDao = marketSummeryViewDao;
    }

    public String getAspiImage() {
        return aspiImage;
    }

    public String getMpiImage() {
        return mpiImage;
    }

    public String getSelectedLink() {
        return selectedLink;
    }

    public String getAspiChange() {
        return aspiChange;
    }

    public String getMpiChange() {
        return mpiChange;
    }

    public String getAspiChangePresentage() {
        return aspiChangePresentage;
    }

    public String getMpiChangePresentage() {
        return mpiChangePresentage;
    }

    public String getAspiValue() {
        return aspiValue;
    }

    public String getMpiValue() {
        return mpiValue;
    }

    public String getFontClassASPIChangeValue() {
        return fontClassASPIChangeValue;
    }

    public String getFontClassASPIChangePresentage() {
        return fontClassASPIChangePresentage;
    }

    public String getFontClassMPIChangeValue() {
        return fontClassMPIChangeValue;
    }

    public String getFontClassMPIChangePresentage() {
        return fontClassMPIChangePresentage;
    }

    public MarketSummeryViewDao getMarketSummeryViewDao() {
        return marketSummeryViewDao;
    }

    public String marketIndex() {

        selectedLink = "marketdata";

        DailyMarketSummery dailyMarketSummery = marketSummeryViewDao.getMarketSummeryView();
        aspiChange = dailyMarketSummery.getAspiChange();
        mpiChange = dailyMarketSummery.getMpiChange();
        aspiValue = dailyMarketSummery.getCseAllShareIndex();
        mpiValue = dailyMarketSummery.getMilankaPriceIndex();
        aspiChangePresentage = dailyMarketSummery.getAspiChangePresentage();
        mpiChangePresentage = dailyMarketSummery.getMpiChangePresentage();

        if (null != aspiChange && Double.parseDouble(aspiChange) >= 0) {
            fontClassASPIChangeValue = "cusfont-green";
            fontClassASPIChangePresentage = "cusfont-green";
            aspiImage = "green-up.png";
        }else {
            fontClassASPIChangeValue = "cusfont-red";
            fontClassASPIChangePresentage = "cusfont-red";
            aspiImage = "red-down.png";
        }

        if (null != mpiChange && Double.parseDouble(mpiChange) >= 0) {
            fontClassMPIChangeValue = "cusfont-green";
            fontClassMPIChangePresentage = "cusfont-green";
            mpiImage = "green-up.png";
        }else {
            fontClassMPIChangeValue = "cusfont-red";
            fontClassMPIChangePresentage = "cusfont-red";
            mpiImage = "red-down.png";
        }

        return "index";
    }
}
