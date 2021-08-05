package com.job;

import com.framework.SpringContext;
import com.job.eod.*;
import com.job.framework.StatefulDBAwareJob;
import com.job.realtime.RealTimeMarketData;
import com.job.weekly.CSEWeeklyPDFSave;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/7/11
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuartzScheduler implements SchedulerListener {


    private static Logger log = Logger.getLogger(QuartzScheduler.class);

    private static final String JOB_STATUS = "STATUS";

    private Scheduler scheduler;

    private static QuartzScheduler instance;

    private QuartzScheduler() {}

    public static QuartzScheduler getInstance() {
        if (instance == null) {
            instance = new QuartzScheduler();
        }
        return instance;
    }

    public void startSchedule() {

        try {

            Properties properties = (Properties) SpringContext.getBean("appProperties");

            String realTimeMarketDataCron = properties.getProperty("cron.realTimeMarketDataCron");
            String eodMarketDataCron = properties.getProperty("cron.eodMarketDataCron");
            String eodTradeDataCron = properties.getProperty("cron.eodTradeDataCron");
            String eodTradingSummeryCron = properties.getProperty("cron.eodTradingSummeryCron");
            String eodRubberAuctionCron = properties.getProperty("cron.eodRubberAuctionCron");
            String eodCSEDailyCron = properties.getProperty("cron.cseDailyCron");
            String goldPriceDailyCron = properties.getProperty("cron.goldDailyCron");
            String weeklyCSEWeeklyCron = properties.getProperty("cron.cseWeeklyCron");

            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
            scheduler.addSchedulerListener(this);
            scheduler.start();

            JobDetail job;

            job = new JobDetail("TEST_JOB", "TEST_GROUP", QuartzScheduler.TestJob.class);
/*
            CronTrigger testTrigger = new CronTrigger("testJob", "testGroup", "0 0/2 * * * ?");
            scheduler.scheduleJob(job, testTrigger);
*/

            job = new JobDetail("CSE_RT_MARKET_DATA", "REAL_TIME_JOBS", RealTimeMarketDayDataJob.class);
            CronTrigger triggerRTCron = new CronTrigger("CSE_RT_MARKET_DATA", "REAL_TIME_JOBS", realTimeMarketDataCron);
            scheduler.scheduleJob(job, triggerRTCron);

            job = new JobDetail("CSE_EOD_MARKET_DAY", "EOD_MARKET_DAY_JOBS", QuartzScheduler.EODMarketDayDataJob.class);
            CronTrigger triggerEODCron = new CronTrigger("CSE_EOD_MARKET_DAY", "EOD_MARKET_DAY_JOBS", eodMarketDataCron);
            scheduler.scheduleJob(job, triggerEODCron);

            job = new JobDetail("CSE_EOD_TRADE_DATA", "EOD_TRADE_DATA_JOBS", QuartzScheduler.EODTradeDataJob.class);
            CronTrigger triggerEODTradeDataCron = new CronTrigger("CSE_EOD_TRADE_DATA", "EOD_TRADE_DATA_JOBS", eodTradeDataCron);
           // scheduler.scheduleJob(job, triggerEODTradeDataCron);

            job = new JobDetail("MARKET_EOD_TRADING_SUMMERY", "EOD_MARKET_SUMMERY_JOBS", QuartzScheduler.DailyEODTradingSummeryJob.class);
            CronTrigger triggerEODDalyTradeCron = new CronTrigger("MARKET_EOD_TRADING_SUMMERY", "EOD_MARKET_SUMMERY_JOBS", eodTradingSummeryCron);
            scheduler.scheduleJob(job, triggerEODDalyTradeCron);

            job = new JobDetail("RUBBER_AUCTION_JOB", "EOD_RUBBER_AUCTION_JOBS", QuartzScheduler.DailyEODRubberAuctionDataJob.class);
            CronTrigger triggerEODDalyRubberCron = new CronTrigger("RUBBER_AUCTION_JOB", "EOD_RUBBER_AUCTION_JOBS", eodRubberAuctionCron);
            scheduler.scheduleJob(job, triggerEODDalyRubberCron);

            job = new JobDetail("CSE_DAILY_JOB", "CSE_DAILY_PDF_JOBS", QuartzScheduler.CSEDailyPDFDownloadJob.class);
            CronTrigger triggerEODPDFDalyCron = new CronTrigger("CSE_DAILY_JOB", "CSE_DAILY_PDF_JOBS", eodCSEDailyCron);
            scheduler.scheduleJob(job, triggerEODPDFDalyCron);

            job = new JobDetail("OTHER_DAILY_JOB", "GOLD_PRICE_DAILY_JOBS", QuartzScheduler.GoldPriceDailyJob.class);
            CronTrigger triggerGoldDalyCron = new CronTrigger("OTHER_DAILY_JOB", "GOLD_PRICE_DAILY_JOBS", goldPriceDailyCron);
            scheduler.scheduleJob(job, triggerGoldDalyCron);

            job = new JobDetail("CSE_WEEKLY_JOB", "CSE_WEEKLY_JOBS", QuartzScheduler.CSEWeeklyPDFDownloadJob.class);
            CronTrigger triggerCSEWeeklyCron = new CronTrigger("CSE_WEEKLY_JOB", "CSE_WEEKLY_JOBS", weeklyCSEWeeklyCron);
            scheduler.scheduleJob(job, triggerCSEWeeklyCron);


        } catch (Exception ex) {
            log.error(ex, ex);
        }

    }


    public void stopSchedule() {
        try {
            if (scheduler != null) {
                this.scheduler.shutdown(false);
            }
        } catch (Exception ex) {
            log.error(ex, ex);
        }
    }


    public void jobScheduled(Trigger trigger) {
        log.info(">>>>>>> QuartzScheduler jobScheduled :" + trigger.getFullJobName());
    }

    public void jobUnscheduled(String s, String s1) {
        log.info(">>>>>>> QuartzScheduler jobUnscheduled :" + s + ", " + s1);
    }

    public void triggerFinalized(Trigger trigger) {
        log.info(">>>>>>> QuartzScheduler triggerFinalized :" + trigger.getFullJobName());
    }

    public void triggersPaused(String s, String s1) {
        log.info(">>>>>>> QuartzScheduler triggersPaused :" + s + ", " + s1);
    }

    public void triggersResumed(String s, String s1) {
        log.info(">>>>>>> QuartzScheduler triggersResumed :" + s + ", " + s1);
    }

    public void jobAdded(JobDetail jobDetail) {
        log.info(">>>>>>> QuartzScheduler job added :" + jobDetail.getFullName());
    }

    public void jobDeleted(String s, String s1) {
        log.info(">>>>>>> QuartzScheduler job deleted :" + s + "," + s1);
    }

    public void jobsPaused(String s, String s1) {
        log.info(">>>>>>> QuartzScheduler jobsPaused :" + s + ", " + s1);
    }

    public void jobsResumed(String s, String s1) {
        log.info(">>>>>>> QuartzScheduler jobsResumed :" + s + ", " + s1);
    }

    public void schedulerError(String s, SchedulerException e) {
        log.info(">>>>>>> QuartzScheduler schedulerError : Code=" + e.getErrorCode(), e.getUnderlyingException());
    }

    public void schedulerInStandbyMode() {
        log.info(">>>>>>> QuartzScheduler is in standby mode.");
    }

    public void schedulerStarted() {
        log.info(">>>>>>> QuartzScheduler is started.");
    }

    public void schedulerShutdown() {
        log.info(">>>>>>> QuartzScheduler is shutdown.");
    }

    public void schedulerShuttingdown() {
        log.info(">>>>>>> QuartzScheduler is shutting down.");
    }

    public static class TestJob extends StatefulDBAwareJob {

        @Override
        public void execute(JobExecutionContext jobExecutionContext, String someString) throws Exception {

            System.out.println("JOB RUNNING .....");
            jobExecutionContext.put("STATUS", "SUCCESS");

            log.info("Executing RealTimeMarketDayDataJob.........");

            EodTrades eodTrades = (EodTrades) SpringContext.getBean("eodTrades");
            /* Processing market data....*/
            String jobStatus = eodTrades.processEodTrades();
            jobExecutionContext.put(JOB_STATUS, jobStatus);

        }
    }


    public static class RealTimeMarketDayDataJob extends StatefulDBAwareJob {


        public void execute(JobExecutionContext jobExecutionContext, String someString) throws Exception {

            log.info("Executing RealTimeMarketDayDataJob.........");

            RealTimeMarketData realTimeMarketData = (RealTimeMarketData) SpringContext.getBean("realTimeMarketData");
            /* Processing market data....*/
            String jobStatus = realTimeMarketData.processMarketData();

            jobExecutionContext.put(JOB_STATUS, jobStatus);

        }
    }

    public static class EODMarketDayDataJob extends StatefulDBAwareJob {

        public void execute(JobExecutionContext jobExecutionContext, String someString) throws Exception {

            log.info("Executing EOD Market Summery Job");

            EODMarketData eodMarketData = (EODMarketData) SpringContext.getBean("eodMarketData");
            String jobStatus = eodMarketData.processEODMarketData();

            jobExecutionContext.put(JOB_STATUS, jobStatus);

        }
    }

    public static class EODTradeDataJob extends StatefulDBAwareJob {

        public void execute(JobExecutionContext jobExecutionContext, String someString) throws Exception {

            log.info("Executing EOD Trade Data Job");

            EodTrades eodTrades = (EodTrades) SpringContext.getBean("eodTrades");
            String jobStatus = eodTrades.processEodTrades();
            jobExecutionContext.put(JOB_STATUS, jobStatus);
        }
    }


    public static class DailyEODTradingSummeryJob extends StatefulDBAwareJob {

        public void execute(JobExecutionContext jobExecutionContext, String someString) throws Exception {

            log.info("Executing DailyEODTradingSummery Job");

            EODTradingSummery eodTradingSummery = (EODTradingSummery) SpringContext.getBean("eODTradingSummery");
            String jobStatus = eodTradingSummery.processEODTradingSummery();

            jobExecutionContext.put(JOB_STATUS, jobStatus);
        }
    }

    public static class DailyEODRubberAuctionDataJob extends StatefulDBAwareJob {

        public void execute(JobExecutionContext jobExecutionContext, String someString) throws Exception {

            log.info("Executing DailyEODRubberAuctionData Job");

            EODRubberPrice eODRubberPrice = (EODRubberPrice) SpringContext.getBean("eODRubberAuction");
            String jobStatus = eODRubberPrice.processRubberAuctionPrices();

            jobExecutionContext.put(JOB_STATUS, jobStatus);
        }
    }


    public static class CSEDailyPDFDownloadJob extends StatefulDBAwareJob {

        public void execute(JobExecutionContext jobExecutionContext, String someString) throws Exception {

            log.info("Executing CSEDailyPDFDownload Job");

            CSEDailyPDFSave cseDailyPDFSave = new CSEDailyPDFSave();
            String jobStatus =  cseDailyPDFSave.processPDFSave();
            jobExecutionContext.put(JOB_STATUS, jobStatus);
        }
    }

    public static class GoldPriceDailyJob extends StatefulDBAwareJob {

        public void execute(JobExecutionContext jobExecutionContext, String someString) throws Exception {

            log.info("Executing GoldPriceDailyJob Job");

            GoldPriceDaily goldPriceDaily = (GoldPriceDaily) SpringContext.getBean("goldPriceDaily");
            String jobStatus =  goldPriceDaily.processDayGoldPrice();
            jobExecutionContext.put(JOB_STATUS, jobStatus);
        }
    }



    public static class CSEWeeklyPDFDownloadJob extends StatefulDBAwareJob {

        public void execute(JobExecutionContext jobExecutionContext, String someString) throws Exception {

            log.info("Executing CSEWeeklyPDFDownload Job");

            CSEWeeklyPDFSave cseWeeklyPDFSave = new CSEWeeklyPDFSave();
            String jobStatus = cseWeeklyPDFSave.processPDFSave();
            jobExecutionContext.put(JOB_STATUS, jobStatus);
        }
    }
}
