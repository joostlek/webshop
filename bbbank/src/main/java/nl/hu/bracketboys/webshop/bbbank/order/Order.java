package nl.hu.bracketboys.webshop.bbbank.order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "order_generator", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();

    protected Order() {
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        this.items.add(item);
    }
}
