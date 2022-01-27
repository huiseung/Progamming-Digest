package com.example.batch.job.apiJob.chunk.processor;

import com.example.batch.entity.api.ApiRequestDto;
import com.example.batch.entity.product.ProductDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class ProcessorClassifier<C, T> implements Classifier<C, T> {
    private Map<String, ItemProcessor<ProductDto, ApiRequestDto>> processorMap = new HashMap<>();

    @Override
    public T classify(C classifiable) {
        return (T)processorMap.get(((ProductDto)classifiable).getType());
    }

    public void setProcessorMap(Map<String, ItemProcessor<ProductDto, ApiRequestDto>> processorMap) {
        this.processorMap = processorMap;
    }
}
