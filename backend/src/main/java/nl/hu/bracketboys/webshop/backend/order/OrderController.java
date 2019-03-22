package nl.hu.bracketboys.webshop.backend.order;

import nl.hu.bracketboys.webshop.backend.order.dto.NewOrderDTO;
import nl.hu.bracketboys.webshop.backend.order.dto.OrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        return convertToEntity(orderService.saveOrder(orderDTO));
    }

    private OrderDTO convertToEntity(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

}
