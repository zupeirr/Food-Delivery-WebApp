package com.fooddelivery.controller;

import com.fooddelivery.model.OrderItem;
import com.fooddelivery.repository.OrderItemRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemRepository orderItemRepository;
    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    @GetMapping
    public List<OrderItem> getAll() { return orderItemRepository.findAll(); }
    @PostMapping
    public OrderItem create(@RequestBody OrderItem orderItem) { return orderItemRepository.save(orderItem); }
    @GetMapping("/{id}")
    public OrderItem getById(@PathVariable Long id) { return orderItemRepository.findById(id).orElse(null); }
    @PutMapping("/{id}")
    public OrderItem update(@PathVariable Long id, @RequestBody OrderItem oi) {
        return orderItemRepository.findById(id).map(item -> {
            item.setOrder(oi.getOrder());
            item.setMenuItem(oi.getMenuItem());
            item.setQuantity(oi.getQuantity());
            return orderItemRepository.save(item);
        }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { orderItemRepository.deleteById(id); }
} 