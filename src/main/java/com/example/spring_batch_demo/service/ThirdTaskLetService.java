package com.example.spring_batch_demo.service;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;


@Service
public class ThirdTaskLetService implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        // Access Job Execution Context from Step (Job Level)
        System.out.println("Kiem tra: 3rd tasklet");
        System.out.println("Kiem tra JOB EXEC CONTEXT from third Task Let: "+chunkContext.getStepContext().getJobExecutionContext());
        System.out.println("Kiem tra JOB EXEC PARAMETER from third Task Let: "+chunkContext.getStepContext().getJobParameters());
        System.out.println("Kiem tra STEP EXECUTION CONTEXT from third Task Let: "+chunkContext.getStepContext().getStepExecutionContext());
        System.out.println("Kiem tra SYSTEM PROPERTIES from third Task Let: "+chunkContext.getStepContext().getSystemProperties());
        return RepeatStatus.FINISHED;
    }
}
