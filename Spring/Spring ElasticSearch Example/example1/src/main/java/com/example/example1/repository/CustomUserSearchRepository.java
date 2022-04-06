package com.example.example1.repository;

import com.example.example1.entity.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomUserSearchRepository {
    List<User> findAllByAge(Integer age, Pageable pageable);
}
