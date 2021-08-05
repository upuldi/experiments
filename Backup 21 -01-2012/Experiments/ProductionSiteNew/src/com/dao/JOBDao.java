package com.dao;

import com.domain.Job;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 7/14/11
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public interface JOBDao {

    long saveJob(Job job);

    Job getJob(long l);

    void updateJob(Job startedJob);

    Job getJobByNameAndGroup(String jobName, String jobGroup, String date);
}
