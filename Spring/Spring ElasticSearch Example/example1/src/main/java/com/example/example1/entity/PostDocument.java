package com.example.example1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;


@Getter
@Document(indexName="posts")
@NoArgsConstructor
public class PostDocument {
    private Long id;
    private String title;
    private String content;
}
