// File: src/main/java/com/example/demo/service/User/USousCategorieService.java
package com.example.demo.service.User;

import com.example.demo.dto.SousCategorieDTO;

import java.util.List;

public interface USousCategorieService {
    List<SousCategorieDTO> getAllSousCategories();
    SousCategorieDTO getSousCategorieById(Long id);
    List<SousCategorieDTO> getSousCategoriesByCategorieId(Long categorieId);
}