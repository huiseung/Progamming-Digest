package com.example.batch.entity.api;


import com.example.batch.entity.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiRequestDto {
    private Long id;
    private ProductDto productDto;
    private ApiResponseDto apiResponseDto;

    public void setApiResponseDto(ApiResponseDto responseDto){
        this.apiResponseDto = apiResponseDto;
    }
}
