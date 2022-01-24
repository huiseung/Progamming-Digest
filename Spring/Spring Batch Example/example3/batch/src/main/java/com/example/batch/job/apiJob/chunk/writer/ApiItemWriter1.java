package com.example.batch.job.apiJob.chunk.writer;


import com.example.batch.entity.api.ApiRequestDto;
import com.example.batch.entity.api.ApiResponseDto;
import com.example.batch.job.apiJob.service.AbstractApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@Slf4j
public class ApiItemWriter1 extends FlatFileItemWriter<ApiRequestDto> {

    private final AbstractApiService apiService;

    public ApiItemWriter1(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List<? extends ApiRequestDto> items) throws Exception {

        System.out.println("----------------------------------");
        items.forEach(item -> System.out.println("items = " + item));
        System.out.println("----------------------------------");

        ApiResponseDto response = apiService.service(items);
        System.out.println("response = " + response);

        items.forEach(item -> item.setApiResponseDto(response));

        super.setResource(new FileSystemResource("C:\\batch\\product1.txt"));
        super.open(new ExecutionContext());
        super.setLineAggregator(new DelimitedLineAggregator<>());
        super.setAppendAllowed(true);
        super.write(items);
    }
}