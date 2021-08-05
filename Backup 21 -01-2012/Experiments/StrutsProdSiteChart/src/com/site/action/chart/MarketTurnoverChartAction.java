package com.site.action.chart;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/3/11
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketTurnoverChartAction extends ActionSupport {

    private String selectedLink;

    public String getSelectedLink() {
        return selectedLink;
    }

    public void setSelectedLink(String selectedLink) {
        this.selectedLink = selectedLink;
    }

    public String marketTurnover() {

        selectedLink = "marketdata";
        return SUCCESS;
    }
}
