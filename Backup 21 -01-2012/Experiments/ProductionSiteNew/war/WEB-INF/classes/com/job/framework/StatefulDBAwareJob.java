package com.job.framework;

import com.dao.JOBDao;
import com.domain.Job;
import com.framework.SpringContext;
import com.util.UniversalTime;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/11/11
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class StatefulDBAwareJob implements StatefulJob {

    protected StatefulDBAwareJob() {

        this.jobDao = (JOBDao) SpringContext.getBean("jobDao");

    }

    private static Logger log = Logger.getLogger(StatefulDBAwareJob.class);

    private JOBDao jobDao;

    public final void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String someString = init(jobExecutionContext);
        try{
            execute(jobExecutionContext,someString);
        }catch (Exception e) {

            DateTime now  = UniversalTime.getTime();
           // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

            Long jobId = Long.parseLong(someString);
            Job startedJob = jobDao.getJob(jobId.longValue());
            startedJob.setStatus("ERROR");
            startedJob.setComment(e.getMessage() + " : "+ e.toString());
            startedJob.setEndTime(now.toString("yyyy/MM/dd hh:mm:ss"));
            jobDao.updateJob(startedJob);

            errorLog(e);
            throw new JobExecutionException(e);
        }
        close(jobExecutionContext,someString);

    }


    private String init(JobExecutionContext jobExecutionContext) {

        DateTime currentTime = UniversalTime.getTime();

        Date localDate = currentTime.toDate();
/*        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeParser = new SimpleDateFormat("hh:mm:ss:");*/

        String jobName = jobExecutionContext.getJobDetail().getName();
        String jobGroup = jobExecutionContext.getJobDetail().getGroup();
        String date = currentTime.toString("yyyy-MM-dd");

        Job startedJob  = jobDao.getJobByNameAndGroup(jobName,jobGroup,date);
        long jobId = 0;

        if (null == startedJob) {

            Job job = new Job();
            job.setDate(currentTime.toString("yyyy-MM-dd"));
            job.setStartTime(currentTime.toString("hh:mm:ss:"));
            job.setEvent(jobExecutionContext.getJobDetail().getGroup());
            job.setJobName(jobExecutionContext.getJobDetail().getName());
            job.setStatus("STARTED");
            jobId = jobDao.saveJob(job);
        }
        else {
            jobId = startedJob.getId();
            log.debug("Job Found : " + jobId);
        }

        return jobId+"";

    }

    public abstract void execute(JobExecutionContext jobExecutionContext, String someString)
            throws Exception;

    private void close(JobExecutionContext jobExecutionContext, String someString){

        log.debug("Completion of the JOB......Id " + someString);
        log.debug("jobExecutionContext.getTrigger().getNextFireTime()" + jobExecutionContext.getTrigger().getNextFireTime());

        Date nextFireTime = jobExecutionContext.getTrigger().getNextFireTime();

        Date now  = UniversalTime.getLocalTime();
        Date nextFireTimeLocal = UniversalTime.getLocalTime(nextFireTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

        if (now.getDate() < nextFireTimeLocal.getDate() ) {

            log.debug("Job completed for today...");
            Long jobId = Long.parseLong(someString);
            Job startedJob = jobDao.getJob(jobId.longValue());
            startedJob.setStatus("COMPLETED");
            startedJob.setEndTime(simpleDateFormat.format(now));

            String comment = "UNKNOWN";

            if (null != jobExecutionContext.get("STATUS")) {
               comment = (String) jobExecutionContext.get("STATUS");
            }

            startedJob.setComment(comment);

            jobDao.updateJob(startedJob);
        }


    }

    private void errorLog(Exception e) {
        log.debug("Exception Handeled in the DB..");
    }



}
