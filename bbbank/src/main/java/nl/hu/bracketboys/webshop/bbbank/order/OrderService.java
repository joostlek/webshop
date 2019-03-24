package nl.hu.bracketboys.webshop.bbbank.order;

import nl.hu.bracketboys.webshop.bbbank.order.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
    }
}
