package nl.hu.bracketboys.webshop.bbbank.order.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order not found!");
    }
}
