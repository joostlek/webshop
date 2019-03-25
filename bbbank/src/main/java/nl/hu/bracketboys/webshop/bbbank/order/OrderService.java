package nl.hu.bracketboys.webshop.bbbank.order;

import nl.hu.bracketboys.webshop.bbbank.order.exceptions.OrderNotFoundException;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceInterface {

    public final static String PAID_ORDER_ROUTING_KEY = "order.paid";

    private final OrderRepository orderRepository;

    private final RabbitTemplate rabbitTemplate;

    private final Exchange exchange;

    @Autowired
    public OrderService(OrderRepository orderRepository, RabbitTemplate rabbitTemplate, Exchange exchange) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return this.orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public void updateOrderStatus(Long orderId) {
        Order order = this.getOrderById(orderId);
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        rabbitTemplate.convertAndSend(exchange.getName(), PAID_ORDER_ROUTING_KEY, orderId);
    }
}
