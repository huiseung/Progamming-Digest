package com.example.batch.entity.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private int price;
    private String type;
}
