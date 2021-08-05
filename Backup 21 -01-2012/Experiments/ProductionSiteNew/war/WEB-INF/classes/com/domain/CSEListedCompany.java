package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/2/11
 * Time: 10:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class CSEListedCompany {

    private long id;
    private String companyName;
    private String stockCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}
