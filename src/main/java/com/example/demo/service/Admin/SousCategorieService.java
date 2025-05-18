// service/SousCategorieService.java
package com.example.demo.service.Admin;

import com.example.demo.dto.SousCategorieDTO;
import com.example.demo.model.SousCategorie;

import java.util.List;

public interface SousCategorieService {
    SousCategorie getSousCategorieById(long id);
    SousCategorie createSousCategorie(SousCategorieDTO sousCategorieDTO);
    void deleteSousCategorie(long id);
    List<SousCategorie> findByCategorieId(long categorieId);
    List<SousCategorie> getAllSousCategories();
    SousCategorieDTO updateSousCategorie(long id, SousCategorieDTO sousCategorieDTO);
    SousCategorieDTO convertToDTO(SousCategorie sousCategorie);

}