package nl.hu.bracketboys.webshop.bbbank.order;

public interface OrderServiceInterface {
    Order getOrderById(Long orderId);

    void updateOrderStatus(Long orderId);
}
