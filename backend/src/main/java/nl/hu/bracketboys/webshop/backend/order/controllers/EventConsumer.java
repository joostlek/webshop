package nl.hu.bracketboys.webshop.backend.order.controllers;

import nl.hu.bracketboys.webshop.backend.order.service.OrderServiceInterface;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {
    @Autowired
    private OrderServiceInterface orderService;

    @RabbitListener(queues = "orderQueue")
    public void receive(String event) {
        orderService.updateOrderStatus(Long.parseLong(event));
    }
}
