package com.framework;

import com.job.QuartzScheduler;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/6/11
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationStartup implements ServletContextListener {

    private static Logger log = Logger.getLogger(ApplicationStartup.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("##### THE APPLICATION STARTED #####");
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("##### THE APPLICATION STOPPED #####");
    }


}
