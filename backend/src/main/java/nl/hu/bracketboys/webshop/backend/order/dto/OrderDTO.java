package nl.hu.bracketboys.webshop.backend.order.dto;

import nl.hu.bracketboys.webshop.backend.address.dto.AddressDTO;
import nl.hu.bracketboys.webshop.backend.user.dto.UserDTO;

import java.util.List;

public class OrderDTO {
    private Long id;
    private UserDTO user;
    private AddressDTO address;
    private List<OrderItemDTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
