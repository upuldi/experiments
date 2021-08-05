package com.dao;

import com.domain.Job;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/14/11
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class JOBDaoHibernate extends HibernateDaoSupport implements JOBDao {

    public long saveJob(Job job) {
        getHibernateTemplate().save(job);
        return job.getId();
    }

    public Job getJob(long l) {
        return (Job) getHibernateTemplate().get(Job.class,l);
    }

    public void updateJob(Job startedJob) {
        getHibernateTemplate().update(startedJob);
    }

    public Job getJobByNameAndGroup(String jobName, String jobGroup, String date) {

        Query query = getSession().getNamedQuery("getJobByNameGroupAndDate");
        query.setString("date",date);
        query.setString("jobName",jobName);

        List list = query.list();
        Job returnObj=null;

        if ( null != list && list.size()> 0) {
             returnObj = (Job) list.get(0);
        }
        return returnObj;
    }

}
