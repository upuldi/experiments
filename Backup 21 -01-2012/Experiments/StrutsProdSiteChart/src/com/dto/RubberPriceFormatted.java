package com.dto;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/30/11
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class RubberPriceFormatted implements Comparable<RubberPriceFormatted> {

    private String date;
    private Integer tpc1x;
    private Integer tpc2;
    private Integer tpc3;
    private Integer tpc4;
    private Integer brownCrepe1x;
    private Integer brownCrepe2x;
    private Integer brownCrepe3x;
    private Integer brownCrepe4x;
    private Integer flatBark;
    private Integer rss1;
    private Integer rss2;
    private Integer rss3;
    private Integer rss4;
    private Integer rss5;
    private Integer skimCrepe;
    private Integer soleCrepe;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTpc1x() {
        return tpc1x;
    }

    public void setTpc1x(Integer tpc1x) {
        this.tpc1x = tpc1x;
    }

    public Integer getTpc2() {
        return tpc2;
    }

    public void setTpc2(Integer tpc2) {
        this.tpc2 = tpc2;
    }

    public Integer getTpc3() {
        return tpc3;
    }

    public void setTpc3(Integer tpc3) {
        this.tpc3 = tpc3;
    }

    public Integer getTpc4() {
        return tpc4;
    }

    public void setTpc4(Integer tpc4) {
        this.tpc4 = tpc4;
    }

    public Integer getBrownCrepe1x() {
        return brownCrepe1x;
    }

    public void setBrownCrepe1x(Integer brownCrepe1x) {
        this.brownCrepe1x = brownCrepe1x;
    }

    public Integer getBrownCrepe2x() {
        return brownCrepe2x;
    }

    public void setBrownCrepe2x(Integer brownCrepe2x) {
        this.brownCrepe2x = brownCrepe2x;
    }

    public Integer getBrownCrepe3x() {
        return brownCrepe3x;
    }

    public void setBrownCrepe3x(Integer brownCrepe3x) {
        this.brownCrepe3x = brownCrepe3x;
    }

    public Integer getBrownCrepe4x() {
        return brownCrepe4x;
    }

    public void setBrownCrepe4x(Integer brownCrepe4x) {
        this.brownCrepe4x = brownCrepe4x;
    }

    public Integer getFlatBark() {
        return flatBark;
    }

    public void setFlatBark(Integer flatBark) {
        this.flatBark = flatBark;
    }

    public Integer getRss1() {
        return rss1;
    }

    public void setRss1(Integer rss1) {
        this.rss1 = rss1;
    }

    public Integer getRss2() {
        return rss2;
    }

    public void setRss2(Integer rss2) {
        this.rss2 = rss2;
    }

    public Integer getRss3() {
        return rss3;
    }

    public void setRss3(Integer rss3) {
        this.rss3 = rss3;
    }

    public Integer getRss4() {
        return rss4;
    }

    public void setRss4(Integer rss4) {
        this.rss4 = rss4;
    }

    public Integer getRss5() {
        return rss5;
    }

    public void setRss5(Integer rss5) {
        this.rss5 = rss5;
    }

    public Integer getSkimCrepe() {
        return skimCrepe;
    }

    public void setSkimCrepe(Integer skimCrepe) {
        this.skimCrepe = skimCrepe;
    }

    public Integer getSoleCrepe() {
        return soleCrepe;
    }

    public void setSoleCrepe(Integer soleCrepe) {
        this.soleCrepe = soleCrepe;
    }

    public int compareTo(RubberPriceFormatted o) {

        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyyy-MM-dd");
        try {

            Date thisdate = simpleDateFormat.parse(this.date);
            Date foundDate = simpleDateFormat.parse(o.getDate());

            return thisdate.compareTo(foundDate);


        } catch (ParseException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
