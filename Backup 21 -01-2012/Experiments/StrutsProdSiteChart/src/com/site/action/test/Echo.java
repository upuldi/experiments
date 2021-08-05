package com.site.action.test;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 10/21/11
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Echo extends ActionSupport {

    private String echo;

    public String getEcho() {
        return echo;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }


    public String echoMessage() throws Exception {
        setEcho("Default yo");
        return SUCCESS;
    }

}
