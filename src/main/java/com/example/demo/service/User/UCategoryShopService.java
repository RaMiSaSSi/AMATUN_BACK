package com.example.demo.service.User;

import com.example.demo.model.CategoryShop;

import java.util.List;

public interface UCategoryShopService {
    List<CategoryShop> getAllCategoryShops();
    CategoryShop getCategoryShopById(Long id);

}
