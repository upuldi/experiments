package com.site.action.ajax;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 10/31/11
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class StockCodesAction  extends ActionSupport {

    private String data = "Afghanistan, Zimbabwe, India, United States, Germany, China, Israel";
    private List<String> countries;
    private String country;

    public String execute() {
        countries = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(data, ",");

        while (st.hasMoreTokens()) {
            countries.add(st.nextToken().trim());
        }
        return SUCCESS;
    }
    public String getCountry() {
        return this.country;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
