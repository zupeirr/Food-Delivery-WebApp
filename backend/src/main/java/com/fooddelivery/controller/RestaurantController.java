package com.fooddelivery.controller;

import com.fooddelivery.model.Restaurant;
import com.fooddelivery.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    @GetMapping
    public List<Restaurant> getAll() { return restaurantRepository.findAll(); }
    @PostMapping
    public Restaurant create(@RequestBody Restaurant restaurant) { return restaurantRepository.save(restaurant); }
    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id) { return restaurantRepository.findById(id).orElse(null); }
    @PutMapping("/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant r) {
        return restaurantRepository.findById(id).map(rest -> {
            rest.setName(r.getName()); rest.setAddress(r.getAddress());
            return restaurantRepository.save(rest);
        }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { restaurantRepository.deleteById(id); }
} 