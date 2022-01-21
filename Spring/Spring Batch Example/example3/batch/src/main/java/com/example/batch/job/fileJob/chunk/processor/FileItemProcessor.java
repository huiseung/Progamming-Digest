package com.example.batch.job.fileJob.chunk.processor;

import com.example.batch.entity.product.Product;
import com.example.batch.entity.product.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;

public class FileItemProcessor implements ItemProcessor<ProductDto, Product> {
    @Override
    public Product process(ProductDto item) throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(item, Product.class);
    }
}
