package com.domain;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 1/17/12
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoldPrice {
    
    private long id;
    private Date date;
    private String goldOunce;
    private String goldPound;
    private String gramKarat24;
    private String gramKarat22;
    private String gramKarat21;
    private String gramKarat18;
    private String gramKarat14;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGoldOunce() {
        return goldOunce;
    }

    public void setGoldOunce(String goldOunce) {
        this.goldOunce = goldOunce;
    }

    public String getGoldPound() {
        return goldPound;
    }

    public void setGoldPound(String goldPound) {
        this.goldPound = goldPound;
    }

    public String getGramKarat24() {
        return gramKarat24;
    }

    public void setGramKarat24(String gramKarat24) {
        this.gramKarat24 = gramKarat24;
    }

    public String getGramKarat22() {
        return gramKarat22;
    }

    public void setGramKarat22(String gramKarat22) {
        this.gramKarat22 = gramKarat22;
    }

    public String getGramKarat21() {
        return gramKarat21;
    }

    public void setGramKarat21(String gramKarat21) {
        this.gramKarat21 = gramKarat21;
    }

    public String getGramKarat18() {
        return gramKarat18;
    }

    public void setGramKarat18(String gramKarat18) {
        this.gramKarat18 = gramKarat18;
    }

    public String getGramKarat14() {
        return gramKarat14;
    }

    public void setGramKarat14(String gramKarat14) {
        this.gramKarat14 = gramKarat14;
    }
}
