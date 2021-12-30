package com.example.cache;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Member {
    private long id;
    private String email;
    private String name;

    public Member(long id, String email, String name){
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
