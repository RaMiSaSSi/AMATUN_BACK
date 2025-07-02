package com.example.demo.service.Admin;

import com.example.demo.model.CategoryShop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCategoryShopService {
    CategoryShop createCategoryShop(CategoryShop categoryShop);
    CategoryShop updateCategoryShop(Long id, CategoryShop categoryShop);
    void deleteCategoryShop(Long id);
    CategoryShop getCategoryShopById(Long id);
    List<CategoryShop> getAllCategoryShops();
    Page<CategoryShop> getCategoryShopsWithPagination(Pageable pageable); // New method

}
