package nl.hu.bracketboys.webshop.backend.discount.dto;

import java.util.Date;

public class DiscountDTO {
    private Long id;

    private double discount;

    private int productId;

    private Date created;

    private Date updated;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public double getDiscount() { return discount; }

    public void setDiscount(double discount) { this.discount = discount; }

    public int getProductId() { return productId; }

    public void setProductId(int productId) { this.productId = productId; }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
}
