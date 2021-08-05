package com.site.action.chart;

import com.domain.EODData;
import com.manager.CSEListedCompanyManager;
import com.manager.EODDataManager;
import com.opensymphony.xwork2.ActionSupport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/4/11
 * Time: 12:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class RealtimeChartAction extends ActionSupport {

    private String stockCode;
    private String dateString;
    private String selectedLink;
    private boolean showChart = false;
    private boolean showSubheader = false;
    private List<String> stockCodes;
    private EODDataManager eodDataManager;
    private Date date;
    String dateStringSearch;
    private EODData eodData;
    private String param;


    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    private CSEListedCompanyManager cseListedCompanyManager;

    public void setCseListedCompanyManager(CSEListedCompanyManager cseListedCompanyManager) {
        this.cseListedCompanyManager = cseListedCompanyManager;
    }

    public String getDateStringSearch() {
        return dateStringSearch;
    }

    public void setDateStringSearch(String dateStringSearch) {
        this.dateStringSearch = dateStringSearch;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getModel() {
        return eodData;
    }

    public EODData getEodData() {
        return eodData;
    }

    public void setEodData(EODData eodData) {
        this.eodData = eodData;
    }

    public void setEodDataManager(EODDataManager eodDataManager) {
        this.eodDataManager = eodDataManager;
    }

    public boolean isShowSubheader() {
        return showSubheader;
    }

    public void setShowSubheader(boolean showSubheader) {
        this.showSubheader = showSubheader;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public boolean isShowChart() {
        return showChart;
    }

    public void setShowChart(boolean showChart) {
        this.showChart = showChart;
    }

    public String getSelectedLink() {
        return selectedLink;
    }

    public void setSelectedLink(String selectedLink) {
        this.selectedLink = selectedLink;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public List<String> getStockCodes() {
        return stockCodes;
    }

    public void setStockCodes(List<String> stockCodes) {
        this.stockCodes = stockCodes;
    }

    private void init() {

        selectedLink = "realtime";
        stockCodes = cseListedCompanyManager.getCSEStockCodes();
    }

    public String inputData() {

        init();

        eodData = null;
        showSubheader = false;
        showChart = false;
        stockCode = null;

        return SUCCESS;
    }

    public String submitData() {

        init();
        /* Set the search time format */
        Date searchDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyyMMdd");
        dateStringSearch = simpleDateFormatServer.format(searchDate);

        param = stockCode + "|" + dateStringSearch.trim();
        //param = stockCode + "|" + "20111109";

        showChart = true;

        EODData eodData = eodDataManager.getEODDataForStock(dateStringSearch, stockCode);

        if (null != eodData) {
            this.eodData = eodData;
            showSubheader = true;
        }
        return SUCCESS;
    }
}
