package com.example.demo.service.PersonnelBoutique;

import com.example.demo.dto.SousCategorieDTO;
import com.example.demo.model.SousCategorie;

import java.util.List;

public interface PBSousCategorieService {
    SousCategorieDTO getSousCategorieById(Long personnelId, Long sousCategorieId);
    List<SousCategorieDTO> getAllSousCategories(Long personnelId);
    SousCategorieDTO createSousCategorie(Long personnelId, Long categorieId, SousCategorie sousCategorie);
    SousCategorieDTO updateSousCategorie(Long personnelId, Long sousCategorieId, SousCategorie sousCategorie);
    void deleteSousCategorie(Long personnelId, Long sousCategorieId);
    List<SousCategorieDTO> getSousCategoriesByCategorie(Long personnelId, Long categorieId);
}