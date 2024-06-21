package com.example.spring_batch_demo.service;

import com.example.spring_batch_demo.request.JobParamsRequest;
import io.micrometer.core.instrument.util.AbstractPartition;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobService {
    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("firstJob")
    @Autowired
    private Job firstJob;

    @Qualifier("secondJob")
    @Autowired
    private Job secondJob;

    @Autowired
    private JobRepository jobRepository;

    private JobParameters jobParameters;
        @Async
        public  void startJob(String jobName, List<JobParamsRequest> jobParamsRequestList) {
            //b1: Tạo Map<String,JobParameter<?>> params
            Map<String, JobParameter<?>> params = new HashMap<>();
            params.put("currentTime",new JobParameter<>(System.currentTimeMillis(), Long.class));

            jobParamsRequestList.forEach(jobParamsRequest -> {
                params.put(jobParamsRequest.getParamKey(),new JobParameter<>(jobParamsRequest.getParamValue(),String.class));
            });
            //b2: Tạo JobParameters từ thông số params ở b1
            jobParameters = new JobParameters(params);

            try{
                JobExecution jobExecution = null;
                if(jobName.equals("firstJob")){
                    jobExecution=jobLauncher.run(firstJob,jobParameters);
                } else if (jobName.equals("secondJob")) {
                    jobExecution=jobLauncher.run(secondJob,jobParameters);
                }
                System.out.println("Job Execution Id: "+jobExecution.getId());
            }catch (Exception e){
                System.out.println("Loi start job: "+e);
            }

        }
    // Đang tim Job Execution Id
    public List<Long> findJobExecutionIdByName(String jobName){

        List<Long> listJobExecId = new ArrayList<>();
        JobInstance foundJobInstance= jobRepository.findJobInstancesByName(jobName,0,1).get(0);
        List<JobExecution> jobExecutionList  = jobRepository.findJobExecutions(foundJobInstance);
        jobExecutionList.stream().forEach(jobExecution -> {
            listJobExecId.add(jobExecution.getId());
        });

        return listJobExecId;
    }
}
