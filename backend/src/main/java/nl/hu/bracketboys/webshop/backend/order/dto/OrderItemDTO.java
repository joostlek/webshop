package nl.hu.bracketboys.webshop.backend.order.dto;

import nl.hu.bracketboys.webshop.backend.product.dto.ProductDTO;

public class OrderItemDTO {
    private Long id;
    private Long amount;
    private ProductDTO product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
