package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/29/11
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class CSEJSNode {

    private String id;
    private String ticker;
    private String change;
    private String changePresentage;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
