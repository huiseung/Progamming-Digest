package com.example.example1.entity.user;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;

@Getter
@Document(indexName="users")
@Table(name="users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //
    @Column
    private String name;
    @Column
    private Integer age;
    //
    protected User(){}

    @Builder
    @PersistenceConstructor
    public User(Long id, String name, Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
    //
}
