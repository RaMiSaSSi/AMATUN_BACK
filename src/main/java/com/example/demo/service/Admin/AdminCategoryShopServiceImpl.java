package com.example.demo.service.Admin;

import com.example.demo.model.CategoryShop;
import com.example.demo.repository.CategoryShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCategoryShopServiceImpl implements AdminCategoryShopService {
    @Autowired
    private  CategoryShopRepository categoryShopRepository;



    @Override
    public CategoryShop createCategoryShop(CategoryShop categoryShop) {
        return categoryShopRepository.save(categoryShop);
    }

    @Override
    public CategoryShop updateCategoryShop(Long id, CategoryShop categoryShop) {
        CategoryShop existingCategoryShop = categoryShopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryShop not found"));
        existingCategoryShop.setName(categoryShop.getName());
        existingCategoryShop.setLogoPath(categoryShop.getLogoPath());
        return categoryShopRepository.save(existingCategoryShop);
    }

    @Override
    public void deleteCategoryShop(Long id) {
        categoryShopRepository.deleteById(id);
    }

    @Override
    public CategoryShop getCategoryShopById(Long id) {
        return categoryShopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryShop not found"));
    }

    @Override
    public List<CategoryShop> getAllCategoryShops() {
        return categoryShopRepository.findAll();
    }
    @Override
    public Page<CategoryShop> getCategoryShopsWithPagination(Pageable pageable) {
        return categoryShopRepository.findAll(pageable);
    }
}
