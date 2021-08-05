package com.site.action.chart;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/3/11
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketDomesticActivityAction extends ActionSupport {

    private String selectedLink;

    public String getSelectedLink() {
        return selectedLink;
    }

    public void setSelectedLink(String selectedLink) {
        this.selectedLink = selectedLink;
    }

    public String marketDomesticActivity() {

        selectedLink = "marketdata";
        return SUCCESS;
    }
}
