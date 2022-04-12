package com.example.example1.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
    @Override
    public ElasticsearchOperations elasticsearchOperations(
            ElasticsearchConverter elasticsearchConverter,
            RestHighLevelClient elasticsearchClient){
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }

    @Override
    public RestHighLevelClient elasticsearchClient() {
        String host = "localhost:9200";
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(host)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
