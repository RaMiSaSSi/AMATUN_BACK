// BoutiqueServiceImpl.java
package com.example.demo.service.Admin;

import com.example.demo.dto.BoutiqueDTO;
import com.example.demo.dto.CategorieDTO;
import com.example.demo.model.Adresse;
import com.example.demo.model.Boutique;
import com.example.demo.model.BoutiqueCategorie;
import com.example.demo.model.Categorie;
import com.example.demo.repository.AdresseRepository;
import com.example.demo.repository.BoutiqueCategorieRepository;
import com.example.demo.repository.BoutiqueRepository;
import com.example.demo.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoutiqueServiceImpl implements BoutiqueService {

    @Autowired
    private BoutiqueRepository boutiqueRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private BoutiqueCategorieRepository boutiqueCategorieRepository;


    @Override
    public BoutiqueDTO createBoutique(BoutiqueDTO boutiqueDTO) {
        Boutique boutique = new Boutique();
        boutique.setNom(boutiqueDTO.getNom());
        boutique.setEmail(boutiqueDTO.getEmail());
        boutique.setTelephone(boutiqueDTO.getTelephone());
        boutique.setCategoryShopId(boutiqueDTO.getCategoryShopId());
        if (boutiqueDTO.getImage() != null) {
            boutique.setImage(boutiqueDTO.getImage());
        }

        if (boutiqueDTO.getBanner() != null) {
            boutique.setBanner(boutiqueDTO.getBanner());
        }
        if (boutiqueDTO.getImagePath() != null) {
            boutique.setImagePath(boutiqueDTO.getImagePath());
        }

        if (boutiqueDTO.getBannerPath() != null) {
            boutique.setBannerPath(boutiqueDTO.getBannerPath());
        }

        Adresse adresse = adresseRepository.findById(boutiqueDTO.getAdresseId())
                .orElseThrow(() -> new IllegalArgumentException("Adresse not found with id: " + boutiqueDTO.getAdresseId()));
        boutique.setAdresse(adresse);

        if (boutiqueDTO.getCategorieIds() != null) {
            boutiqueDTO.getCategorieIds().forEach(categorieId -> {
                if (boutique.getBoutiqueCategories().stream().noneMatch(bc -> bc.getCategorie().getId() == categorieId)) {
                    BoutiqueCategorie boutiqueCategorie = new BoutiqueCategorie();
                    boutiqueCategorie.setBoutique(boutique);
                    boutiqueCategorie.setCategorie(categorieRepository.findById(categorieId)
                            .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + categorieId)));
                    boutique.getBoutiqueCategories().add(boutiqueCategorie);
                }
            });
        }

        return boutiqueRepository.save(boutique).getDTO();
    }

    @Override
    public BoutiqueDTO updateBoutique(long id, BoutiqueDTO boutiqueDTO) {
        Boutique boutique = boutiqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + id));
        boutique.setNom(boutiqueDTO.getNom());
        boutique.setEmail(boutiqueDTO.getEmail());
        boutique.setTelephone(boutiqueDTO.getTelephone());
        boutique.setCategoryShopId(boutiqueDTO.getCategoryShopId());
        if (boutiqueDTO.getImagePath() != null) {
            boutique.setImagePath(boutiqueDTO.getImagePath());
        }

        if (boutiqueDTO.getBannerPath() != null) {
            boutique.setBannerPath(boutiqueDTO.getBannerPath());
        }

        Adresse adresse = adresseRepository.findById(boutiqueDTO.getAdresseId())
                .orElseThrow(() -> new IllegalArgumentException("Adresse not found with id: " + boutiqueDTO.getAdresseId()));
        boutique.setAdresse(adresse);

        boutique.getBoutiqueCategories().clear();
        if (boutiqueDTO.getCategorieIds() != null) {
            boutiqueDTO.getCategorieIds().forEach(categorieId -> {
                BoutiqueCategorie boutiqueCategorie = new BoutiqueCategorie();
                boutiqueCategorie.setBoutique(boutique);
                boutiqueCategorie.setCategorie(categorieRepository.findById(categorieId)
                        .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + categorieId)));
                boutique.getBoutiqueCategories().add(boutiqueCategorie);
            });
        }

        return boutiqueRepository.save(boutique).getDTO();
    }

    @Override
    public void deleteBoutique(long id) {
        boutiqueRepository.deleteById(id);
    }

    @Override
    public BoutiqueDTO getBoutiqueById(long id) {
        return boutiqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + id))
                .getDTO();
    }

    @Override
    public Page<BoutiqueDTO> getAllBoutiques(Pageable pageable) {
        return boutiqueRepository.findAll(pageable)
                .map(boutique -> {
                    BoutiqueDTO boutiqueDTO = boutique.getDTO();
                    boutiqueDTO.setImagePath(boutique.getImagePath());
                    boutiqueDTO.setBannerPath(boutique.getBannerPath());
                    return boutiqueDTO;
                });
    }
    @Override
    public List<CategorieDTO> getCategoriesByBoutiqueId(long boutiqueId) {
        Boutique boutique = boutiqueRepository.findById(boutiqueId)
                .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + boutiqueId));
        return boutique.getBoutiqueCategories().stream()
                .map(boutiqueCategorie -> convertToDTO(boutiqueCategorie.getCategorie()))
                .collect(Collectors.toList());
    }
    @Override
    public void removeCategorieFromBoutique(long boutiqueId, long categorieId) {
        Boutique boutique = boutiqueRepository.findById(boutiqueId)
                .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + boutiqueId));
        BoutiqueCategorie boutiqueCategorie = boutique.getBoutiqueCategories().stream()
                .filter(bc -> bc.getCategorie().getId() == categorieId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + categorieId + " in boutique with id: " + boutiqueId));
        boutique.getBoutiqueCategories().remove(boutiqueCategorie);
        boutiqueCategorieRepository.delete(boutiqueCategorie); // Ensure this line is present to delete the relationship
        boutiqueRepository.save(boutique);
    }

    @Override
    public void addCategorieToBoutique(long boutiqueId, long categorieId) {
        Boutique boutique = boutiqueRepository.findById(boutiqueId)
                .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + boutiqueId));
        if (boutique.getBoutiqueCategories().stream().noneMatch(bc -> bc.getCategorie().getId() == categorieId)) {
            BoutiqueCategorie boutiqueCategorie = new BoutiqueCategorie();
            boutiqueCategorie.setBoutique(boutique);
            boutiqueCategorie.setCategorie(categorieRepository.findById(categorieId)
                    .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + categorieId)));
            boutique.getBoutiqueCategories().add(boutiqueCategorie);
            boutiqueRepository.save(boutique);
        } else {
            // Handle the case where the category already exists
            System.out.println("Categorie with id: " + categorieId + " already exists in boutique with id: " + boutiqueId);
        }
    }

    @Override
    public List<CategorieDTO> getUnassociatedCategories(long boutiqueId) {
        Boutique boutique = boutiqueRepository.findById(boutiqueId)
                .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + boutiqueId));
        List<Long> associatedCategoryIds = boutique.getBoutiqueCategories().stream()
                .map(bc -> bc.getCategorie().getId())
                .collect(Collectors.toList());
        List<Categorie> unassociatedCategories = categorieRepository.findAll().stream()
                .filter(categorie -> !associatedCategoryIds.contains(categorie.getId()))
                .collect(Collectors.toList());
        return unassociatedCategories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CategorieDTO convertToDTO(Categorie categorie) {
        CategorieDTO dto = new CategorieDTO();
        dto.setId(categorie.getId());
        dto.setNom(categorie.getNom());
        dto.setImagePath(categorie.getImagePath());
        return dto;
    }
}