package com.example.example1.repository;

import com.example.example1.entity.user.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {
}
