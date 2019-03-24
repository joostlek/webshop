package nl.hu.bracketboys.webshop.bbbank.order.controller;

import nl.hu.bracketboys.webshop.bbbank.order.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OrderController {

    private final OrderServiceInterface orderService;

    @Autowired
    public OrderController(OrderServiceInterface orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/{orderId}")
    public String acceptOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.getOrderById(orderId));
        return "accept";
    }

    @PostMapping("/order/{orderId}")
    public RedirectView back(@PathVariable Long orderId, RedirectAttributes attributes) {
        orderService.updateOrderStatus(orderId);
        return new RedirectView("http://localhost:8082");
    }
}
