package com.example.batch.job.fileJob;

import com.example.batch.entity.product.Product;
import com.example.batch.entity.product.ProductDto;
import com.example.batch.job.fileJob.chunk.processor.FileItemProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;


@RequiredArgsConstructor
@Configuration
public class FileJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;


    @Bean
    public Job fileJob(){
        return jobBuilderFactory.get("fileJob")
                .start(fileStep())
                .build();
    }

    @Bean
    public Step fileStep(){
        return stepBuilderFactory.get("fileStep")
                .<ProductDto, Product>chunk(10)
                .reader(fileItemReader(null))
                .processor(fileItemProcessor())
                .writer(fileItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<ProductDto> fileItemReader(
            @Value("#{jobParameters['request']}") String request
    ){
        return new FlatFileItemReaderBuilder<ProductDto>()
                .name("flatFile")
                .resource(new ClassPathResource("product_"+request+".csv"))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(ProductDto.class)
                .linesToSkip(1)
                .delimited().delimiter(",")
                .names("id", "name", "price", "type")
                .build();
    }

    @Bean
    public ItemProcessor<ProductDto, Product> fileItemProcessor(){
        return new FileItemProcessor();
    }

    @Bean
    public JpaItemWriter<Product> fileItemWriter(){
        return new JpaItemWriterBuilder<Product>()
                .entityManagerFactory(entityManagerFactory)
                .usePersist(true)
                .build();
    }
}
