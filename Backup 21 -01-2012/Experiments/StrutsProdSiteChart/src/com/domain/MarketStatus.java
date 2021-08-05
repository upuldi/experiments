package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 12/12/11
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketStatus {

    private String turnover;
    private String volume;
    private String positiveContributors;
    private String negativeContributors;

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
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
