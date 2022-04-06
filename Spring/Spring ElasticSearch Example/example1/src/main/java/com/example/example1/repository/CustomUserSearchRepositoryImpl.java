package com.example.example1.repository;

import com.example.example1.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomUserSearchRepositoryImpl implements CustomUserSearchRepository{
    private final ElasticsearchOperations elasticsearchOperations;


    @Override
    public List<User> findAllByAge(Integer age, Pageable pageable) {
        Criteria criteria = Criteria.where("age").contains()
    }
}
