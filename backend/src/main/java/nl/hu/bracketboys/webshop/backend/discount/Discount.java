package nl.hu.bracketboys.webshop.backend.discount;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "discounts")
public class Discount {

    @Id
    @SequenceGenerator(name = "discount_generator", sequenceName = "discount_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_generator")
    private Long id;

    @Column(nullable = false)
    private double discount;

    @Column(nullable = false)
    private int productId;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    public Discount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
}