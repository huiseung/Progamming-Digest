package com.example.batch.job.apiJob.chunk.processor;

import com.example.batch.entity.api.ApiRequestDto;
import com.example.batch.entity.product.ProductDto;
import org.springframework.batch.item.ItemProcessor;

public class ApiItemProcessor2 implements ItemProcessor<ProductDto, ApiRequestDto> {
    @Override
    public ApiRequestDto process(ProductDto item) throws Exception {
        return ApiRequestDto.builder()
                .id(item.getId())
                .productDto(item)
                .build();
    }
}
