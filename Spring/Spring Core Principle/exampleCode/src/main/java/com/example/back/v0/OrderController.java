package com.example.back.v0;


import com.example.back.v1.OrderServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v0/order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceV1 orderService;

    @GetMapping("/{itemId}")
    public String request(@PathVariable(value = "itemId") String itemId){
        orderService.orderItem(itemId);
        return "ok";
    }
}
