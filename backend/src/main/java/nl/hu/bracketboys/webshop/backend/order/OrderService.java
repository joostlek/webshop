package nl.hu.bracketboys.webshop.backend.order;

import nl.hu.bracketboys.webshop.backend.address.AddressServiceInterface;
import nl.hu.bracketboys.webshop.backend.order.dto.NewOrderDTO;
import nl.hu.bracketboys.webshop.backend.order.dto.NewOrderItemDTO;
import nl.hu.bracketboys.webshop.backend.order.exceptions.OrderNotFoundException;
import nl.hu.bracketboys.webshop.backend.product.ProductServiceInterface;
import nl.hu.bracketboys.webshop.backend.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;

    private final UserServiceInterface userService;

    private final AddressServiceInterface addressService;

    private final ProductServiceInterface productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserServiceInterface userService, AddressServiceInterface addressService, ProductServiceInterface productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.productService = productService;
    }

    @Override
    public Order getOrderByUserId(Long userId) {
        return orderRepository.findById(userId)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    @Transactional
    public Order saveOrder(NewOrderDTO order) {
        OrderBuilder orderBuilder = new OrderBuilder()
                .setAddress(addressService.getAddressById(order.getAddressId()))
                .setUser(userService.getUserById(order.getUserId()));
        for (NewOrderItemDTO itemDTO : order.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAmount(itemDTO.getAmount());
            orderItem.setProduct(productService.getProductById(itemDTO.getProductId()));
            orderBuilder.addItem(orderItem);
        }
        return orderRepository.save(orderBuilder.build());
    }
}
