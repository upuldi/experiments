package com.site.framework;

import org.apache.log4j.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 10/31/11
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationStartup implements ServletContextListener {

    private static Logger log = Logger.getLogger(ApplicationStartup.class);

    public void contextInitialized(ServletContextEvent ServletContextEvent) {

        log.info("@@@@@@ STRUTS APP STARTED @@@@@@");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        log.info("@@@@ STRUTS APP STOPED.. @@@@@");
    }

}
