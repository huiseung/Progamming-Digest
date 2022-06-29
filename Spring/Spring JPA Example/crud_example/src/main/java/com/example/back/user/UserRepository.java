package com.example.back.user;

import com.example.back.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select DISTINCT u from User u join fetch u.posts")
//    List<User> findAllPostsJoinFetch();
}
