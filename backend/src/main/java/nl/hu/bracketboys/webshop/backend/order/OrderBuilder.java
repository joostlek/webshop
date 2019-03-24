package nl.hu.bracketboys.webshop.backend.order;


import nl.hu.bracketboys.webshop.backend.address.Address;
import nl.hu.bracketboys.webshop.backend.user.User;

public class OrderBuilder {
    private Order order;

    public OrderBuilder() {
        this.order = new Order();
        this.order.setOrderStatus(OrderStatus.NEW);
    }

    public OrderBuilder setAddress(Address address) {
        this.order.setAddress(address);
        return this;
    }

    public OrderBuilder setUser(User user) {
        this.order.setUser(user);
        return this;
    }

    public OrderBuilder addItem(OrderItem orderItem) {
        this.order.addItem(orderItem);
        return this;
    }

    public Order build() {
        return this.order;
    }
}
