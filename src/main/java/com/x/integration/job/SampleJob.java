package com.x.integration.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author PanLei
 * @version 1.0.0
 * @createTime 2023-07-06
 */
public class SampleJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(SampleJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Sample job is executed");
    }
}
