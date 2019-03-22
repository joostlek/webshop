package nl.hu.bracketboys.webshop.backend.discount.dto;

import nl.hu.bracketboys.webshop.backend.product.Product;

import java.util.Date;

public class DiscountDTO {
    private Long id;

    private double discount;

    private Date created;

    private Date updated;

    private Product product;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public double getDiscount() { return discount; }

    public void setDiscount(double discount) { this.discount = discount; }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
