package com.example.back.config;

public enum CacheEntryTtl {
    DEFAULT(60),
    POST(60*3);

    private int value;
    CacheEntryTtl(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
