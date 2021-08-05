package com.site.action.chart;

import com.manager.CSEListedCompanyManager;
import com.opensymphony.xwork2.ActionSupport;


import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/2/11
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CandleChartAction  extends ActionSupport {

    private String stockCode;
    private String selectedLink;
    private boolean showChart= false;
    private List<String> stockCodes;
    private CSEListedCompanyManager cseListedCompanyManager;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getSelectedLink() {
        return selectedLink;
    }

    public void setSelectedLink(String selectedLink) {
        this.selectedLink = selectedLink;
    }

    public boolean isShowChart() {
        return showChart;
    }

    public void setShowChart(boolean showChart) {
        this.showChart = showChart;
    }

    public List<String> getStockCodes() {
        return stockCodes;
    }

    public void setStockCodes(List<String> stockCodes) {
        this.stockCodes = stockCodes;
    }

    public void setCseListedCompanyManager(CSEListedCompanyManager cseListedCompanyManager) {
        this.cseListedCompanyManager = cseListedCompanyManager;
    }

    private void init(){

        selectedLink = "charts";
        stockCodes = cseListedCompanyManager.getCSEStockCodes();
    }

    public String inputData() {

        init();

        showChart = false;
        stockCode = null;

        return SUCCESS;
    }

    public String submitData() {

        init();

        if (null != stockCode) {
            showChart = true;
        }

        return SUCCESS;
    }

}
