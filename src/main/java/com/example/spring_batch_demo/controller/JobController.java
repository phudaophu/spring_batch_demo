package com.example.spring_batch_demo.controller;


import com.example.spring_batch_demo.request.JobParamsRequest;
import com.example.spring_batch_demo.service.JobService;
import org.hibernate.dialect.SpannerSqlAstTranslator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobOperator jobOperator;

    @GetMapping("/start/{jobName}")
    public ResponseEntity<?> startJob(@PathVariable String jobName, @RequestBody List<JobParamsRequest> jobParamsRequestList){

        jobService.startJob(jobName,jobParamsRequestList);

        return new ResponseEntity<>("Job Started",HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findJob(@RequestParam String jobName){

      List<Long> listJobExecutionId =  jobService.findJobExecutionIdByName(jobName);

        return new ResponseEntity<>(listJobExecutionId,HttpStatus.OK);
    }


    @GetMapping("/stop")
    public ResponseEntity<?> stopJob(@RequestParam long id) throws NoSuchJobExecutionException, JobExecutionNotRunningException {


        boolean checkIsStop=jobOperator.stop(id);


        return new ResponseEntity<>("JobId "+id+" is Stop= "+checkIsStop,HttpStatus.OK);
    }



}
