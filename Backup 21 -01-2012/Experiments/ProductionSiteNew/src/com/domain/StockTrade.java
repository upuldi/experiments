package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/18/11
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockTrade {

    private long id;
    private String date;
    private String ticker;
    private String time;
    private long tradeVol;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getTradeVol() {
        return tradeVol;
    }

    public void setTradeVol(long tradeVol) {
        this.tradeVol = tradeVol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
