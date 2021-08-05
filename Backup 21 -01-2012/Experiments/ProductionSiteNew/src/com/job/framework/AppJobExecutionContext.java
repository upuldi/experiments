package com.job.framework;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.spi.TriggerFiredBundle;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/14/11
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppJobExecutionContext extends JobExecutionContext {

    public AppJobExecutionContext(Scheduler scheduler, TriggerFiredBundle firedBundle, Job job) {
        super(scheduler, firedBundle, job);
    }

}
