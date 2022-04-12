package com.example.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class Example1Application {

    public static void main(String[] args) {
        SpringApplication.run(Example1Application.class, args);
    }

}
