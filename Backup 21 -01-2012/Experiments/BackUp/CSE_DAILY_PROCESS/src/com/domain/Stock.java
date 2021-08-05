package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/26/11
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stock {

    private long id;
    private String tickerString;
    private String tickerCode;
    private String boardType;
    private String sector;
    private String vwaPrice;
    private String lastTradedPRice;
    private String lastTradedDate;
    private String high;
    private String low;
    private String foreginHolding;
    private String issuedQuantity;
    private String turnover;
    private String indexedMarketCap;
    private String qtyInCDS;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getTickerString() {
        return tickerString;
    }

    public void setTickerString(String tickerString) {
        this.tickerString = tickerString;
    }

    public String getTickerCode() {
        return tickerCode;
    }

    public void setTickerCode(String tickerCode) {
        this.tickerCode = tickerCode;
    }

    public String getVwaPrice() {
        return vwaPrice;
    }

    public void setVwaPrice(String vwaPrice) {
        this.vwaPrice = vwaPrice;
    }

    public String getLastTradedPRice() {
        return lastTradedPRice;
    }

    public void setLastTradedPRice(String lastTradedPRice) {
        this.lastTradedPRice = lastTradedPRice;
    }

    public String getLastTradedDate() {
        return lastTradedDate;
    }

    public void setLastTradedDate(String lastTradedDate) {
        this.lastTradedDate = lastTradedDate;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getForeginHolding() {
        return foreginHolding;
    }

    public void setForeginHolding(String foreginHolding) {
        this.foreginHolding = foreginHolding;
    }

    public String getIssuedQuantity() {
        return issuedQuantity;
    }

    public void setIssuedQuantity(String issuedQuantity) {
        this.issuedQuantity = issuedQuantity;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getIndexedMarketCap() {
        return indexedMarketCap;
    }

    public void setIndexedMarketCap(String indexedMarketCap) {
        this.indexedMarketCap = indexedMarketCap;
    }

    public String getQtyInCDS() {
        return qtyInCDS;
    }

    public void setQtyInCDS(String qtyInCDS) {
        this.qtyInCDS = qtyInCDS;
    }

    @Override
    public String toString() {

        return tickerString + " : " + vwaPrice + " : " + lastTradedPRice +
                " : " + lastTradedDate + " : " + high + " : " + low + " : " + foreginHolding + " : " + issuedQuantity + " : " + turnover +
                " : " + indexedMarketCap + " : " + qtyInCDS;
    }

    public String toCVSString() {

        return tickerString + " , " + tickerCode + " , " + boardType + " , " + sector + " , " + vwaPrice + " , " + lastTradedPRice +
                " , " + lastTradedDate + " , " + high + " , " + low + " , " + foreginHolding + " , " + issuedQuantity + " , " + turnover +
                " , " + indexedMarketCap + " , " + qtyInCDS;
    }


}
