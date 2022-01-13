package com.example.back.v3;

import com.example.back.trace.LogTrace;
import com.example.back.trace.TraceId;
import com.example.back.trace.TraceStatus;
import com.example.back.trace.helloTrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/*
* 저장에 1000ms가 걸린다 가정, 실제 저장을 하진 않는다
* itemId로 ex를 받으면 예외가 발생환 상황이라 가정
* */


@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace trace;

    public void save(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            //저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int mills){
        try{
            Thread.sleep(mills);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
