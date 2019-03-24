package nl.hu.bracketboys.webshop.backend.order.dto;

import java.util.List;

public class NewOrderDTO {
    private Long userId;

    private Long addressId;

    private List<NewOrderItemDTO> orderItems;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public List<NewOrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<NewOrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
