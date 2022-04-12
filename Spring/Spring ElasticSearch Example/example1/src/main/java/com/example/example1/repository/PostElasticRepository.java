package com.example.example1.repository;

import com.example.example1.entity.PostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostElasticRepository extends ElasticsearchRepository<PostDocument, Long> {
}
