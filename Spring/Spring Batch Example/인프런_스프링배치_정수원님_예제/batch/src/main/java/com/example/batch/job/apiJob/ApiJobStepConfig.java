package com.example.batch.job.apiJob;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@Configuration
public class ApiJobStepConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final Step apiManagerStep;
    private final JobLauncher jobLauncher;

    @Bean
    public Step apiJobStep() throws Exception{
        return stepBuilderFactory.get("apiJobStep")
                .job(apiPartitionJob())
                .launcher(jobLauncher)
                .build();
    }

    @Bean
    public Job apiPartitionJob() throws Exception{
        return jobBuilderFactory.get("apiPartitionJob")
                .start(apiManagerStep)
                .build();
    }
}
