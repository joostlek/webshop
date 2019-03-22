package nl.hu.bracketboys.webshop.backend.order;

import nl.hu.bracketboys.webshop.backend.order.dto.NewOrderDTO;

import java.util.List;

public interface OrderServiceInterface {
    List<Order> getOrdersByUserId(Long userId);

    Order saveOrder(NewOrderDTO order);
}
