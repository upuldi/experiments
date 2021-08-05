package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/24/11
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class TradeProcess {

    private long id;
    private String date;
    private String ticker;
    private String processed;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
        this.processed = processed;
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
