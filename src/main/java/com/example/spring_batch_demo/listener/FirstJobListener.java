package com.example.spring_batch_demo.listener;


import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

    @Override
     public void beforeJob(JobExecution jobExecution) {
        // Access Job Execution
        System.out.println("before job - kiem tra job name "+jobExecution.getJobInstance().getJobName());
        System.out.println("before job - kiem tra job parameter "+jobExecution.getJobParameters());
        System.out.println("before job - kiem tra job execution context "+ jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("test key","test value");

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // Access Job Execution
        System.out.println("after job - kiem tra job name "+jobExecution.getJobInstance().getJobName());
        System.out.println("after job - kiem tra job parameter "+jobExecution.getJobParameters());
        System.out.println("after job - kiem tra job execution context "+ jobExecution.getExecutionContext());
    }

}
