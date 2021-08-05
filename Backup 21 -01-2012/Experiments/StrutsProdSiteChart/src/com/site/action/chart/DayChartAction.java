package com.site.action.chart;

import com.domain.EODData;
import com.manager.CSEListedCompanyManager;
import com.manager.EODDataManager;
import com.opensymphony.xwork2.ActionSupport;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 10/25/11
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class DayChartAction extends ActionSupport {

    private String stockCode;
    private String dateString;
    private String selectedLink;
    private boolean showChart= false;
    private boolean showTable= false;
    private boolean showSubheader= false;
    private List<String> stockCodes;
    private EODDataManager eodDataManager;
    private Date date;
    String dateStringSearch;
    private EODData eodData;

    private String closeClass;
    private String change;
    private String changeClass;
    private String changeClassPresentage;
    private String changePresentage;
    private String imageLink;
    private String formatedVolume;
    private String formatedTrades;


    private CSEListedCompanyManager cseListedCompanyManager;

    public String getChangeClassPresentage() {
        return changeClassPresentage;
    }

    public String getFormatedTrades() {
        return formatedTrades;
    }

    public String getFormatedVolume() {
        return formatedVolume;
    }

    public String getCloseClass() {
        return closeClass;
    }

    public String getChange() {
        return change;
    }

    public String getChangeClass() {
        return changeClass;
    }

    public String getChangePresentage() {
        return changePresentage;
    }

    public String getImageLink() {
        return imageLink;
    }

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

    public boolean isShowTable() {
        return showTable;
    }

    private void init(){

        selectedLink = "charts";
        stockCodes = cseListedCompanyManager.getCSEStockCodes();
    }

    public String inputData() {

        init();

        eodData = null;
        showSubheader = false;
        showChart = false;
        showTable = false;
        stockCode = null;

        Date timeNow = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        dateString = simpleDateFormat.format(timeNow);

        return SUCCESS;
    }

    public String submitData() {

        init();

        try {
        if (null != dateString) {

            /* Set the search time format */
            SimpleDateFormat simpleDateFormatServer = new SimpleDateFormat("yyyyMMdd");
            dateStringSearch = simpleDateFormatServer.format(date);

            /* Set back the time */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            dateString = simpleDateFormat.format(date);
            date = null;

            showChart = true;

            EODData eodData = eodDataManager.getEODDataForStock(dateStringSearch, stockCode);

            if (null != eodData) {

                this.eodData = eodData;

                NumberFormat numberFormat = NumberFormat.getInstance();

                formatedVolume = numberFormat.format(eodData.getVol());
                formatedTrades = eodData.getTrade();


                if (null != eodData.getChange() && null != eodData.getChangePresentage()) {

                    change = eodData.getChange();
                    changePresentage = eodData.getChangePresentage();

                } else {


                    double openVal = numberFormat.parse(eodData.getOpen()).doubleValue();
                    double closeVal = numberFormat.parse(eodData.getClose()).doubleValue();
                    double changeVal = closeVal - openVal;
                    double changePresentageVal = (changeVal/openVal)*100;

                     BigDecimal bdChange = new BigDecimal(changeVal).setScale(2, RoundingMode.HALF_EVEN);
                     double changeRounded = bdChange.doubleValue();

                    BigDecimal bdChangePresent = new BigDecimal(changePresentageVal).setScale(2, RoundingMode.HALF_EVEN);
                    double changePresentageDbRounded = bdChangePresent.doubleValue();


                    change = changeRounded+"";
                    changePresentage = changePresentageDbRounded + "";
                }

                if (null == change || change.isEmpty()) {
                    change = "0";
                    changePresentage = "0";
                }

                if (Double.parseDouble(change) >= 0 ) {
                    changeClass = "cusfont-big-green";
                    changeClassPresentage = "cusfont-green";
                    imageLink = "green-up.png";
                } else {
                    changeClass = "cusfont-big-red";
                    changeClassPresentage = "cusfont-red";
                    imageLink = "red-down.png";
                }

                showSubheader = true;
                showTable = true;
            }



        }    }catch (Exception e) {
            e.printStackTrace();

        }

        return SUCCESS;
    }
}
