package com.example.demo.service.PersonnelBoutique;

import com.example.demo.dto.CategorieDTO;

import java.util.List;

public interface PBCategorieService {
    CategorieDTO getCategorieById(Long personnelId, Long categorieId);
    List<CategorieDTO> getAllCategories(Long personnelId);
    void createCategorie(Long personnelId, Long categorieId); // Updated method
    CategorieDTO updateCategorie(Long personnelId, Long categorieId, CategorieDTO categorieDTO);
    void deleteCategorie(Long personnelId, Long categorieId);
}