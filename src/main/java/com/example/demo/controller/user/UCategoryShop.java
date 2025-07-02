package com.example.demo.controller.user;

import com.example.demo.model.CategoryShop;
import com.example.demo.service.User.UCategoryShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/category-shop")
public class UCategoryShop {
    @Autowired
    private UCategoryShopService categoryShopService;

    @GetMapping
    public List<CategoryShop> getAllCategoryShops() {
        return categoryShopService.getAllCategoryShops();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryShop> getCategoryShopById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryShopService.getCategoryShopById(id));
    }
}
