package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 12/19/11
 * Time: 11:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PosNegContributors {

    private long id;
    private String positiveContributors;
    private String negativeContributors;
    private String date;

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

    public String getPositiveContributors() {
        return positiveContributors;
    }

    public void setPositiveContributors(String positiveContributors) {
        this.positiveContributors = positiveContributors;
    }

    public String getNegativeContributors() {
        return negativeContributors;
    }

    public void setNegativeContributors(String negativeContributors) {
        this.negativeContributors = negativeContributors;
    }
}
