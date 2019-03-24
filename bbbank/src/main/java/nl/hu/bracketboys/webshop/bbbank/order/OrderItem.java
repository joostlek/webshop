package nl.hu.bracketboys.webshop.bbbank.order;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @SequenceGenerator(name = "order_item_generator", sequenceName = "order_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Order order;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private double price;

    protected OrderItem() {
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
