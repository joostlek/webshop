package nl.hu.bracketboys.webshop.backend.order.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order not found!");
    }
}
