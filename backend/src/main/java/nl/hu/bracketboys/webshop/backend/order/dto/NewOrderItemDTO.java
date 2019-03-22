package nl.hu.bracketboys.webshop.backend.order.dto;

public class NewOrderItemDTO {
    private Long productId;
    private int amount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
