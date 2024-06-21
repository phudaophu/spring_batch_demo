package com.example.spring_batch_demo.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecondJobScheduler {
    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("secondJob")
    @Autowired
    private Job secondJob;

    //@Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void secondJobStarter(){

        //b1: Tạo Map<String,JobParameter<?>> params
        Map<String, JobParameter<?>> params = new HashMap<>();
        params.put("currentTime",new JobParameter<>(System.currentTimeMillis(), Long.class));
        //b2: Tạo JobParameters từ thông số params ở b1
        JobParameters jobParameters = new JobParameters(params);
        try{

            JobExecution  jobExecution=jobLauncher.run(secondJob,jobParameters);

            System.out.println("Job Execution Id: "+jobExecution.getId());
        }catch (Exception e){
            System.out.println("Loi start job: "+e);
        }

    }

}
