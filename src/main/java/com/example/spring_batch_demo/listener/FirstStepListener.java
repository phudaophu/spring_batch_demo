package com.example.spring_batch_demo.listener;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Before step get Step Name: "+stepExecution.getStepName());
        System.out.println("Before step get Job Execution Context: "+stepExecution.getJobExecution().getExecutionContext());
        System.out.println("Before step get Step Execution Context: "+stepExecution.getExecutionContext());
        System.out.println("Before step get Status: "+stepExecution.getStatus());
        System.out.println("Before step get Exit Status: "+stepExecution.getExitStatus());
        System.out.println("Before step get Summary: "+stepExecution.getSummary());
        System.out.println("Before step get Id: "+stepExecution.getId());
        System.out.println("Before step get commit count: "+stepExecution.getCommitCount());
        System.out.println("Before step get last update: "+stepExecution.getLastUpdated());
        System.out.println("Before step get write count: "+stepExecution.getWriteCount());
        stepExecution.getExecutionContext().putString("step key","step value");
    }


    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("After step get Step Name: "+stepExecution.getStepName());
        System.out.println("After step get Job Execution Context: "+stepExecution.getJobExecution().getExecutionContext());
        System.out.println("After step get Step Execution Context: "+stepExecution.getExecutionContext());
        System.out.println("After step get Status: "+stepExecution.getStatus());
        System.out.println("After step get Exit Status: "+stepExecution.getExitStatus());
        System.out.println("After step get Summary: "+stepExecution.getSummary());
        System.out.println("After step get Id: "+stepExecution.getId());
        System.out.println("After step get commit count: "+stepExecution.getCommitCount());
        System.out.println("After step get last update: "+stepExecution.getLastUpdated());
        System.out.println("After step get write count: "+stepExecution.getWriteCount());
        return null;
    }


}
