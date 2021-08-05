package com.framework;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/7/11
 * Time: 5:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpringContext {

    private static Logger log = Logger.getLogger(SpringContext.class);


    /**
     * This method is used to return the App context to the application.
     * @param beanId
     * @return
     */
    public static Object getBean(String beanId) {

        log.debug("Obtaining bean : " + beanId);
        ApplicationContext ctx = AppContext.getApplicationContext();

        if (null == ctx) {
            throw new RuntimeException("Null Context Found ....... ");
        }

        return ctx.getBean(beanId);


    }

}
