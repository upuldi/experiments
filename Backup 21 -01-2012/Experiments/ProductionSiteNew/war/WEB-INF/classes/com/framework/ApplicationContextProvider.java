package com.framework;

import com.job.QuartzScheduler;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/7/11
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationContextProvider implements ApplicationContextAware {

    private static Logger log = Logger.getLogger(ApplicationContextProvider.class);

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("Setting Application context...");
        AppContext.setApplicationContext(applicationContext);
        log.info(" *** STARTING JOB SERVER *** ");
        QuartzScheduler.getInstance().startSchedule();
    }
}
