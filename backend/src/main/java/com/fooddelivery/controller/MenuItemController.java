package com.fooddelivery.controller;

import com.fooddelivery.model.MenuItem;
import com.fooddelivery.repository.MenuItemRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
    private final MenuItemRepository menuItemRepository;
    public MenuItemController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }
    @GetMapping
    public List<MenuItem> getAll() { return menuItemRepository.findAll(); }
    @PostMapping
    public MenuItem create(@RequestBody MenuItem menuItem) { return menuItemRepository.save(menuItem); }
    @GetMapping("/{id}")
    public MenuItem getById(@PathVariable Long id) { return menuItemRepository.findById(id).orElse(null); }
    @PutMapping("/{id}")
    public MenuItem update(@PathVariable Long id, @RequestBody MenuItem m) {
        return menuItemRepository.findById(id).map(item -> {
            item.setName(m.getName()); item.setDescription(m.getDescription()); item.setPrice(m.getPrice());
            item.setRestaurant(m.getRestaurant());
            return menuItemRepository.save(item);
        }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { menuItemRepository.deleteById(id); }
} 