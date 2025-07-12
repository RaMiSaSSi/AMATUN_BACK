package com.example.demo.service.User;

import com.example.demo.dto.CategorieDTO;
import com.example.demo.model.Categorie;
import com.example.demo.model.CategoryShop;

import java.util.List;

public interface UCategoryShopService {
    List<CategoryShop> getAllCategoryShops();
    CategoryShop getCategoryShopById(Long id);
    public List<CategorieDTO> getCategoriesByCategoryShopId(Long categoryShopId);
}
