package nl.hu.bracketboys.webshop.backend.order.controllers;

import nl.hu.bracketboys.webshop.backend.order.Order;
import nl.hu.bracketboys.webshop.backend.order.dto.NewOrderDTO;
import nl.hu.bracketboys.webshop.backend.order.dto.OrderDTO;
import nl.hu.bracketboys.webshop.backend.order.service.OrderServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@BasePathAwareController
public class OrderController {

    private final OrderServiceInterface orderService;

    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderServiceInterface orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/orders")
    public OrderDTO storeOrder(@RequestBody NewOrderDTO orderDTO) {
        return convertToDTO(orderService.saveOrder(orderDTO));
    }

    @GetMapping("/users/{userId}/orders")
    public List<OrderDTO> getOrdersFromUser(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

}
