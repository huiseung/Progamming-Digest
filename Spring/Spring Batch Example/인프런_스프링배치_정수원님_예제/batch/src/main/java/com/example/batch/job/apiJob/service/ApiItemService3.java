package com.example.batch.job.apiJob.service;

import com.example.batch.entity.api.ApiInfo;
import com.example.batch.entity.api.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiItemService3 extends AbstractApiService{

    @Override
    protected ApiResponseDto doApiService(RestTemplate restTemplate, ApiInfo apiInfo) {
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8000/api/product/3", apiInfo, String.class);
        int statusCodeValue = response.getStatusCodeValue();
        ApiResponseDto responseDto = new ApiResponseDto(statusCodeValue+"", response.getBody());
        return responseDto;
    }
}
