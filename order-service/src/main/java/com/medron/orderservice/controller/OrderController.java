package com.medron.orderservice.controller;

import com.medron.basedomains.entity.Order;
import com.medron.basedomains.entity.OrderEvent;
import com.medron.orderservice.kafka.OrderProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {
    private OrderProducer orderProducer;

    @PostMapping
    public String takeOrders(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = OrderEvent.builder()
                .status("PENDING")
                .order(order)
                .message("Order Status is pending.")
                .build();

        orderProducer.sendMessage(orderEvent);
        return "Order send successfully.";
    }

}
