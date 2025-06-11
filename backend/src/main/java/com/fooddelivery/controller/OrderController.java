package com.fooddelivery.controller;

import com.fooddelivery.model.Order;
import com.fooddelivery.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @GetMapping
    public List<Order> getAll() { return orderRepository.findAll(); }
    @PostMapping
    public Order create(@RequestBody Order order) { return orderRepository.save(order); }
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) { return orderRepository.findById(id).orElse(null); }
    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody Order o) {
        return orderRepository.findById(id).map(ord -> {
            ord.setStatus(o.getStatus());
            ord.setRestaurant(o.getRestaurant());
            ord.setUser(o.getUser());
            ord.setOrderItems(o.getOrderItems());
            return orderRepository.save(ord);
        }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { orderRepository.deleteById(id); }
} 