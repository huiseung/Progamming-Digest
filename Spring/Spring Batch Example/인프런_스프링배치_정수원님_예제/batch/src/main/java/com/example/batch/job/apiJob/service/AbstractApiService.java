package com.example.batch.job.apiJob.service;

import com.example.batch.entity.api.ApiInfo;
import com.example.batch.entity.api.ApiRequestDto;
import com.example.batch.entity.api.ApiResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public abstract class AbstractApiService {
    public ApiResponseDto service(List<? extends ApiRequestDto> requests){
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.errorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        }).build();

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiInfo apiInfo = ApiInfo.builder()
                .requests(requests)
                .build();
        HttpEntity<ApiInfo> reqEntity = new HttpEntity<>(apiInfo, headers);

        return doApiService(restTemplate, apiInfo);
    }

    protected abstract ApiResponseDto doApiService(RestTemplate restTemplate, ApiInfo apiInfo);
}
