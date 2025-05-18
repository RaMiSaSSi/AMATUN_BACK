// File: src/main/java/com/example/demo/service/User/UCategorieServiceImpl.java
package com.example.demo.service.User;

import com.example.demo.dto.CategorieDTO;
import com.example.demo.model.Categorie;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.BoutiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UCategorieServiceImpl implements UCategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private BoutiqueRepository boutiqueRepository;

    @Override
    public List<CategorieDTO> getAllCategories() {
        List<Categorie> categories = categorieRepository.findAll();
        return categories.stream().map(Categorie::getDTO).collect(Collectors.toList());
    }

    @Override
    public CategorieDTO getCategorieById(Long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + id));
        return categorie.getDTO();
    }

    @Override
    public List<CategorieDTO> getCategoriesByBoutiqueId(Long boutiqueId) {
        return boutiqueRepository.findById(boutiqueId)
                .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + boutiqueId))
                .getBoutiqueCategories().stream()
                .map(boutiqueCategorie -> boutiqueCategorie.getCategorie().getDTO())
                .collect(Collectors.toList());
    }
}