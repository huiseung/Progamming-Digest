package com.example.batch.job.apiJob.util;

import com.example.batch.entity.product.ProductDto;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryGenerator {
    public static ProductDto[] getProductList(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<ProductDto> productDtos = jdbcTemplate.query(
                "select type from product group by type",
                new ProductRowMapper() {
                    @Override
                    public ProductDto mapRow(ResultSet rs, int i) throws SQLException{
                        return ProductDto.builder().type(rs.getString("type")).build();
                    }
                }
        );
        return productDtos.toArray(new ProductDto[]{});
    }

    public static Map<String, Object> getParameterForQuery(String parameter, String value) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(parameter, value);
        return parameters;
    }
}
