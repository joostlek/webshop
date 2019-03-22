package nl.hu.bracketboys.webshop.backend.order;

import nl.hu.bracketboys.webshop.backend.order.dto.NewOrderDTO;

public interface OrderServiceInterface {
    Order getOrderByUserId(Long userId);

    Order saveOrder(NewOrderDTO order);
}
