package com.es.cxp.domainservices.order;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = "${orders.new-orders-queue}", containerFactory = "rabbitListenerContainerFactory")
    public void handleNewOrder(MyPayload payload) {
        System.out.println("New order received: " + payload.content());
    }

    @RabbitListener(queues = "${orders.delivered-orders-queue}", containerFactory = "rabbitListenerContainerFactory")
    public void handleDeliveredOrder(MyPayload payload) {
        System.out.println("Delivered order : " + payload.content());
    }
}
