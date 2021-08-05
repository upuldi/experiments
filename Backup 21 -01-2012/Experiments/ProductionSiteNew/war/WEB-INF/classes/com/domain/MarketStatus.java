package com.domain;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/8/11
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketStatus {

    private long id;
    private String date;
    private String status;
    private Date dateObj;

    public Date getDateObj() {
        return dateObj;
    }

    public void setDateObj(Date dateObj) {
        this.dateObj = dateObj;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

public String getInsetSQL(String db,String tableName) {

    return "INSERT INTO `"+ db +"`.`"+ tableName +"` (`id`, `date`, `status`) VALUES ('', '"+
            date+ "', '"+ status +"');";
    }
}
