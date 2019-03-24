package nl.hu.bracketboys.webshop.backend.order.service;

import nl.hu.bracketboys.webshop.backend.address.service.AddressServiceInterface;
import nl.hu.bracketboys.webshop.backend.order.Order;
import nl.hu.bracketboys.webshop.backend.order.OrderBuilder;
import nl.hu.bracketboys.webshop.backend.order.OrderItem;
import nl.hu.bracketboys.webshop.backend.order.OrderStatus;
import nl.hu.bracketboys.webshop.backend.order.dto.NewOrderDTO;
import nl.hu.bracketboys.webshop.backend.order.dto.NewOrderItemDTO;
import nl.hu.bracketboys.webshop.backend.order.repository.OrderItemRepository;
import nl.hu.bracketboys.webshop.backend.order.repository.OrderRepository;
import nl.hu.bracketboys.webshop.backend.product.service.ProductServiceInterface;
import nl.hu.bracketboys.webshop.backend.user.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;

    private final UserServiceInterface userService;

    private final AddressServiceInterface addressService;

    private final ProductServiceInterface productService;

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserServiceInterface userService, AddressServiceInterface addressService, ProductServiceInterface productService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.productService = productService;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Order saveOrder(NewOrderDTO order) {
        OrderBuilder orderBuilder = new OrderBuilder()
                .setAddress(addressService.getAddressById(order.getAddressId()))
                .setUser(userService.getUserById(order.getUserId()));
        Order newOrder = orderRepository.save(orderBuilder.build());
        for (NewOrderItemDTO itemDTO : order.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAmount(itemDTO.getAmount());
            orderItem.setProduct(productService.getProductById(itemDTO.getProductId()));
            newOrder.addItem(orderItem);
            orderItemRepository.save(orderItem);
        }
        return orderRepository.save(newOrder);
    }

    @Override
    public Order updateOrderStatus(Long orderId) {
        Order order = this.orderRepository.getOne(orderId);
        order.setOrderStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }
}
