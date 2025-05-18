// File: src/main/java/com/example/demo/service/User/UCategorieService.java
package com.example.demo.service.User;

import com.example.demo.dto.CategorieDTO;

import java.util.List;

public interface UCategorieService {
    List<CategorieDTO> getAllCategories();
    CategorieDTO getCategorieById(Long id);
    List<CategorieDTO> getCategoriesByBoutiqueId(Long boutiqueId);
}