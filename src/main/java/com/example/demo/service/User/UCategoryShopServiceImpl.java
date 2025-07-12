package com.example.demo.service.User;

import com.example.demo.dto.CategorieDTO;
import com.example.demo.model.Boutique;
import com.example.demo.model.BoutiqueCategorie;
import com.example.demo.model.Categorie;
import com.example.demo.model.CategoryShop;
import com.example.demo.repository.BoutiqueRepository;
import com.example.demo.repository.CategoryShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UCategoryShopServiceImpl implements UCategoryShopService{
    @Autowired
    private CategoryShopRepository CategoryShopRepository;
    @Autowired
    private BoutiqueRepository boutiqueRepository;

    @Override
    public List<CategoryShop> getAllCategoryShops(){
        return CategoryShopRepository.findAll();
    }
    @Override
    public CategoryShop getCategoryShopById(Long id) {
        return CategoryShopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CategoryShop not found"));
    }
    // Java
    // Java
    @Override
    public List<CategorieDTO> getCategoriesByCategoryShopId(Long categoryShopId) {
        List<Boutique> boutiques = boutiqueRepository.findByCategoryShopId(categoryShopId);

        return boutiques.stream()
                .flatMap(boutique -> boutique.getBoutiqueCategories().stream())
                .map(boutiqueCategorie -> boutiqueCategorie.getCategorie().getDTO())
                .distinct()
                .collect(Collectors.toList());
    }
}
