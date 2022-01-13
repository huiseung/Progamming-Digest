package com.example.back.v3;


import com.example.back.trace.LogTrace;
import com.example.back.trace.TraceId;
import com.example.back.trace.TraceStatus;
import com.example.back.trace.helloTrace.HelloTraceV2;
import com.example.back.v2.OrderRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
