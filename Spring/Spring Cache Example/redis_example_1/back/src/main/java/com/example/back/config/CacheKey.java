package com.example.back.config;

public enum CacheKey {
    POST("POST");

    private String value;

    CacheKey(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
