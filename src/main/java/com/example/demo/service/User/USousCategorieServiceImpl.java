// File: src/main/java/com/example/demo/service/User/USousCategorieServiceImpl.java
package com.example.demo.service.User;

import com.example.demo.dto.SousCategorieDTO;
import com.example.demo.model.SousCategorie;
import com.example.demo.repository.SousCategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class USousCategorieServiceImpl implements USousCategorieService {

    @Autowired
    private SousCategorieRepository sousCategorieRepository;

    @Override
    public List<SousCategorieDTO> getAllSousCategories() {
        List<SousCategorie> sousCategories = sousCategorieRepository.findAll();
        return sousCategories.stream().map(SousCategorie::toDTO).collect(Collectors.toList());
    }

    @Override
    public SousCategorieDTO getSousCategorieById(Long id) {
        SousCategorie sousCategorie = sousCategorieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SousCategorie not found with id: " + id));
        return sousCategorie.toDTO();
    }

    @Override
    public List<SousCategorieDTO> getSousCategoriesByCategorieId(Long categorieId) {
        List<SousCategorie> sousCategories = sousCategorieRepository.findByCategorieId(categorieId);
        return sousCategories.stream().map(SousCategorie::toDTO).collect(Collectors.toList());
    }
}