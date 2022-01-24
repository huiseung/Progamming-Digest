package com.example.batch.job.apiJob.partition;

import com.example.batch.entity.product.ProductDto;
import com.example.batch.job.apiJob.util.QueryGenerator;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ProductPartitioner implements Partitioner {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        ProductDto[] productDtos = QueryGenerator.getProductList(dataSource);
        Map<String, ExecutionContext> result = new HashMap<>();
        int number= 0;
        for(int i = 0; i < productDtos.length; i++){
            ExecutionContext value = new ExecutionContext();
            result.put("partition"+number, value);
            value.put("product", productDtos[i]);
            number += 1;
        }
        return result;
    }
}
