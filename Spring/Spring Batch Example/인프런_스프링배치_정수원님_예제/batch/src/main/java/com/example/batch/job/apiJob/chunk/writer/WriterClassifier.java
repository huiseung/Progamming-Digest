package com.example.batch.job.apiJob.chunk.writer;

import com.example.batch.entity.api.ApiRequestDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class WriterClassifier<C, T> implements Classifier<C, T> {
    private Map<String, ItemWriter<ApiRequestDto>> writerMap = new HashMap<>();

    @Override
    public T classify(C classifiable) {
        return (T) writerMap.get(((ApiRequestDto)classifiable).getProductDto().getType());
    }

    public void setWriterMap(Map<String, ItemWriter<ApiRequestDto>> writerMap){
        this.writerMap = writerMap;
    }
}
