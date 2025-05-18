package com.example.demo.service.Admin;

import com.example.demo.dto.CategorieDTO;
import com.example.demo.dto.SousCategorieDTO;
import com.example.demo.model.Boutique;
import com.example.demo.model.BoutiqueCategorie;
import com.example.demo.model.Categorie;
import com.example.demo.model.SousCategorie;
import com.example.demo.repository.BoutiqueCategorieRepository;
import com.example.demo.repository.BoutiqueRepository;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.SousCategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private SousCategorieRepository sousCategorieRepository;

    @Autowired
    private BoutiqueRepository boutiqueRepository;

    @Autowired
    private BoutiqueCategorieRepository boutiqueCategorieRepository;

    @Override

    public List<CategorieDTO> getAllCategories() {
        return categorieRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

   @Override
public CategorieDTO getCategorieById(long id) {
    Categorie categorie = categorieRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Categorie not found"));
    return categorie.getDTO();
}
    @Override
    public Categorie createCategorie(CategorieDTO categorieDTO) {
        Categorie categorie = convertToEntity(categorieDTO);
        categorie.setImagePath(categorieDTO.getImagePath());
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(long id, CategorieDTO categorieDTO) {
        Categorie existingCategorie = categorieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categorie not found"));
        existingCategorie.setNom(categorieDTO.getNom());
        existingCategorie.setImage(categorieDTO.getImage());
        existingCategorie.setImagePath(categorieDTO.getImagePath());
        return categorieRepository.save(existingCategorie);
    }

    @Override
    public void deleteCategorie(long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public List<SousCategorieDTO> getSousCategoriesByCategorieId(long categorieId) {
        List<SousCategorie> sousCategories = sousCategorieRepository.findByCategorieId(categorieId);
        return sousCategories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // CategorieServiceImpl.java
    @Override
    public void addCategorieToBoutiques(long categorieId, Set<Long> boutiqueIds) {
        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new IllegalArgumentException("Categorie not found"));
        Set<BoutiqueCategorie> boutiqueCategories = boutiqueIds.stream()
                .map(boutiqueId -> {
                    Boutique boutique = boutiqueRepository.findById(boutiqueId)
                            .orElseThrow(() -> new IllegalArgumentException("Boutique not found"));
                    BoutiqueCategorie boutiqueCategorie = new BoutiqueCategorie();
                    boutiqueCategorie.setCategorie(categorie);
                    boutiqueCategorie.setBoutique(boutique);
                    return boutiqueCategorie;
                })
                .collect(Collectors.toSet());
        categorie.setBoutiqueCategories(boutiqueCategories);
        categorieRepository.save(categorie);
    }

    @Override
    public void addExistingCategorieToExistingBoutique(long categorieId, long boutiqueId) {
        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new IllegalArgumentException("Categorie not found"));
        Boutique boutique = boutiqueRepository.findById(boutiqueId)
                .orElseThrow(() -> new IllegalArgumentException("Boutique not found"));
        BoutiqueCategorie boutiqueCategorie = new BoutiqueCategorie();
        boutiqueCategorie.setCategorie(categorie);
        boutiqueCategorie.setBoutique(boutique);
        boutiqueCategorieRepository.save(boutiqueCategorie);
    }

    @Override
    public CategorieDTO getCategorieWithBoutiques(long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categorie not found"));
        return convertToDTO(categorie);
    }

    private CategorieDTO convertToDTO(Categorie categorie) {
        CategorieDTO dto = new CategorieDTO();
        dto.setId(categorie.getId());
        dto.setNom(categorie.getNom());
        dto.setImage(categorie.getImage());
        dto.setImagePath(categorie.getImagePath());
        return dto;
    }

    private Categorie convertToEntity(CategorieDTO categorieDTO) {
        Categorie categorie = new Categorie();
        categorie.setId(categorieDTO.getId());
        categorie.setNom(categorieDTO.getNom());
        categorie.setImage(categorieDTO.getImage());
        return categorie;
    }

    private SousCategorieDTO convertToDTO(SousCategorie sousCategorie) {
        SousCategorieDTO dto = new SousCategorieDTO();
        dto.setId(sousCategorie.getId());
        dto.setNom(sousCategorie.getNom());
        dto.setCategorieId(sousCategorie.getCategorie().getId());
        return dto;
    }
}