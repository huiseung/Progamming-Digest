package com.example.batch.job.apiJob;


import com.example.batch.entity.api.ApiRequestDto;
import com.example.batch.entity.product.ProductDto;
import com.example.batch.job.apiJob.chunk.processor.ApiItemProcessor1;
import com.example.batch.job.apiJob.chunk.processor.ApiItemProcessor2;
import com.example.batch.job.apiJob.chunk.processor.ApiItemProcessor3;
import com.example.batch.job.apiJob.chunk.processor.ProcessorClassifier;
import com.example.batch.job.apiJob.partition.ProductPartitioner;
import com.example.batch.job.apiJob.util.QueryGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class ApiPartitionStepConfig {
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private int chunkSize = 10;

    @Bean
    public Step apiManagerStep() throws Exception{
        ProductDto[] productDtos;
        return stepBuilderFactory.get("apiManagerStep")
                .partitioner(apiWorkerStep().getName(), partitioner())
                .step(apiWorkerStep())
                .gridSize(productDtos.length)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step apiWorkerStep() throws Exception{
        return stepBuilderFactory.get("apiWorkerStep")
                .<ProductDto, ProductDto>chunk(chunkSize)
                .reader(apiWorkerItemReader(null))
                .processor(apiWorkerItemProcessor())
                .writer(apiWorkerItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<ProductDto> apiWorkerItemReader(
            @Value("#{stepExecutionContext['product']}") ProductDto productDto
    ) throws Exception{
        JdbcPagingItemReader<ProductDto> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(dataSource);
        reader.setPageSize(chunkSize);
        reader.setRowMapper(new BeanPropertyRowMapper(ProductDto.class));

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, name, price, type");
        queryProvider.setFromClause("from product");
        queryProvider.setWhereClause("where type = :type");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("id", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);

        reader.setParameterValues(QueryGenerator.getParameterForQuery("type", productDto.getType()));
        reader.setQueryProvider(queryProvider);
        reader.afterPropertiesSet();

        return reader;
    }

    @Bean
    public ItemProcessor apiWorkerItemProcessor(){
        ClassifierCompositeItemProcessor<ProductDto, ApiRequestDto> processor = new ClassifierCompositeItemProcessor<>();
        ProcessorClassifier<ProductDto, ItemProcessor<?, ? extends ApiRequestDto>> classifier = new ProcessorClassifier();
        Map<String, ItemProcessor<ProductDto, ApiRequestDto>> processorMap = new HashMap<>();

        processorMap.put("1", new ApiItemProcessor1());
        processorMap.put("2", new ApiItemProcessor2());
        processorMap.put("3", new ApiItemProcessor3());

        classifier.setProcessorMap(processorMap);
        processor.setClassifier(classifier);

        return processor;
    }

    @Bean
    public ItemWriter apiWorkerItemWriter(){
        ClassifierCompositeItemWriter<ApiRequestDto> writer = new ClassifierCompositeItemWriter<>();
        WriterClassifier<ApiRequestDto, ItemWriter<? super ApiRequestDto>> classifier = new WriterClassifier();

        return writer;
    }

    @Bean
    public ProductPartitioner productPartitioner(){
        ProductPartitioner productPartitioner = new ProductPartitioner()();
        productPartitioner.setDataSource(dataSource);
        return productPartitioner;
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(6);
        taskExecutor.setThreadNamePrefix("apiWorker-thread");
        return taskExecutor;
    }
}
