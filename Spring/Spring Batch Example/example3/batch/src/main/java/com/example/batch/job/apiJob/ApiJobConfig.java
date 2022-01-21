package com.example.batch.job.apiJob;


import com.example.batch.job.apiJob.listener.ApiJobListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ApiJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final Step apiJobStep;

    @Bean
    public Job apiJob() throws Exception{
        return jobBuilderFactory.get("apiJob")
                .start(apiStartStep())
                .next(apiJobStep)
                .next(apiEndStep())
                .incrementer(new RunIdIncrementer())
                .listener(new ApiJobListener())
                .build();
    }

    @Bean
    public Step apiStartStep() throws Exception{
        return stepBuilderFactory.get("apiStartStep")
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>ApiStartStep");
                    return RepeatStatus.FINISHED;
                })).build();
    }


    @Bean
    public Step apiEndStep() throws Exception{
        return stepBuilderFactory.get("apiEndStep")
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>ApiEndStep");
                    return RepeatStatus.FINISHED;
                })).build();
    }
}
