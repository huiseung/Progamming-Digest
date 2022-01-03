package com.example.back.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class OrderRepository {
    public void save(String itemId){
        if(itemId.equals("ex")){
            throw new IllegalArgumentException("error!");
        }
        sleep(1000);
    }

    private void sleep(int mills){
        try{
            Thread.sleep(mills);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
