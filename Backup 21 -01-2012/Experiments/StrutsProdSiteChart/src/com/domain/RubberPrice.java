package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/25/11
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class RubberPrice {

    private long id;
    private String item;
    private String priceLKR;
    private String priceUSD;
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPriceLKR() {

        if (null == priceLKR || "0".equalsIgnoreCase(priceLKR.trim())) {
            return null;
        }else {

            if(priceLKR.contains(".")) {
                String withOutZero = priceLKR.substring(0,priceLKR.indexOf("."));
                if (withOutZero.equalsIgnoreCase("0")) {
                    return null;
                }
                return withOutZero;
            }
            return priceLKR;
        }
    }

    public void setPriceLKR(String priceLKR) {
        this.priceLKR = priceLKR;
    }

    public String getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(String priceUSD) {
        this.priceUSD = priceUSD;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
