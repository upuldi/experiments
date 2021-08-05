package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/23/11
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class EODData {

    private long id;
    private String ticker;
    private String date;
    private String trade;
    private String open;
    private String high;
    private String low;
    private String close;
    private long vol;
    private String lastTreaded;
    private String change;
    private String changePresentage;

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangePresentage() {
        return changePresentage;
    }

    public void setChangePresentage(String changePresentage) {
        this.changePresentage = changePresentage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
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

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public long getVol() {
        return vol;
    }

    public void setVol(long vol) {
        this.vol = vol;
    }

    public String getLastTreaded() {
        return lastTreaded;
    }

    public void setLastTreaded(String lastTreaded) {
        this.lastTreaded = lastTreaded;
    }
}
