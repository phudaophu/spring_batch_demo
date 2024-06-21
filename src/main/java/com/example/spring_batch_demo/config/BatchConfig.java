package com.example.spring_batch_demo.config;

import com.example.spring_batch_demo.entity.FlightEntity;
import com.example.spring_batch_demo.listener.FirstJobListener;
import com.example.spring_batch_demo.listener.FirstStepListener;
import com.example.spring_batch_demo.processor.FirstItemProcessor;
import com.example.spring_batch_demo.reader.FirstItemReader;
import com.example.spring_batch_demo.repository.FlightPrimaryRepository;
import com.example.spring_batch_demo.service.ThirdTaskLetService;
import com.example.spring_batch_demo.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;

@Configuration
public class BatchConfig {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private FlightPrimaryRepository flightPrimaryRepository;
    @Autowired
    private ThirdTaskLetService thirdTaskLetService;
    @Autowired
    private FirstJobListener firstJobListener;
    @Autowired
    private FirstStepListener firstStepListener;
    @Autowired
    private FirstItemReader firstItemReader;
    @Autowired
    private FirstItemProcessor firstItemProcessor;
    @Autowired
    private FirstItemWriter firstItemWriter;


    @Bean
    public Job secondJob(){
        return new JobBuilder("TheSecondJob_test01",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .next(step2())
                .build();
    }

    private Step firstChunkStep(){
        return new StepBuilder("TheFirstChunkStep_demo01",jobRepository)
                .<Integer,String>chunk(4,transactionManager)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }

    @Bean
    public Step step1(){
        return new StepBuilder("TheFirstStep_demo08", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Kiem tra: 1st taslet");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager)
                .listener(firstStepListener)
                .build();
    }
//    @Bean
//    public RepositoryItemReader<FlightEntity> reader() {
////        LocalDate today = LocalDate.now();
////        LocalDate startDate = today.minusDays(2);
////        LocalDate endDate = today.plusDays(2);
//
////        Date startDate = Date.valueOf("2022-03-01");
////        Date endDate =  Date.valueOf("2022-03-02");
//
//        return new RepositoryItemReaderBuilder<FlightEntity>()
//                .name("flightPrimaryItemReader")
//                .repository(flightPrimaryRepository)
//                .methodName("findAll")
//                //.arguments(startDate, endDate)
//                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
//                .pageSize(10)
//                .build();
//    }

    @Bean
    public Step step2(){
        return new StepBuilder("TheTaskLetStep_demo01", jobRepository)
                //.tasklet(secondTask(), transactionManager)
                .tasklet(thirdTaskLetService, transactionManager)
                .listener(firstStepListener)
                .build();
    }

    @Bean
    public Job firstJob(){
        return new JobBuilder("TheFirstJob_test01",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .on("*")
                .to(step2())
                .end()
                .listener(firstJobListener)
                .build();
    }

    public Tasklet secondTask(){
        return new Tasklet(){
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Kiem tra: 2nd tasklet");
                return RepeatStatus.FINISHED;
            }
        };
    }


}
