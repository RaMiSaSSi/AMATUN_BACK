package com.example.demo.service.User;

import com.example.demo.model.CategoryShop;
import com.example.demo.repository.CategoryShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UCategoryShopServiceImpl implements UCategoryShopService{
    @Autowired
    private CategoryShopRepository CategoryShopRepository;

    @Override
    public List<CategoryShop> getAllCategoryShops(){
        return CategoryShopRepository.findAll();
    }
    @Override
    public CategoryShop getCategoryShopById(Long id) {
        return CategoryShopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryShop not found"));
    }
}
