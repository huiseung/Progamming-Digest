package com.example.example1.repository;

import com.example.example1.entity.PostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface PostElasticRepository extends ElasticsearchRepository<PostDocument, Long> {
    List<PostDocument> findPostDocumentsByContentContaining(String content);
}
