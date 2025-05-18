package com.example.demo.service.PersonnelBoutique;

        import com.example.demo.dto.CategorieDTO;
        import com.example.demo.model.BoutiqueCategorie;
        import com.example.demo.model.Categorie;
        import com.example.demo.model.PersonnelBoutique;
        import com.example.demo.repository.BoutiqueCategorieRepository;
        import com.example.demo.repository.CategorieRepository;
        import com.example.demo.repository.PersonnelBoutiqueRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.stream.Collectors;

        @Service
        public class PBCategorieServiceImpl implements PBCategorieService {
            @Autowired
            private PersonnelBoutiqueRepository personnelBoutiqueRepository;

            @Autowired
            private BoutiqueCategorieRepository boutiqueCategorieRepository;

            @Autowired
            private CategorieRepository categorieRepository;

            @Override
            public CategorieDTO getCategorieById(Long personnelId, Long categorieId) {
                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                        .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                Categorie categorie = categorieRepository.findById(categorieId)
                        .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + categorieId));
                BoutiqueCategorie boutiqueCategorie = boutiqueCategorieRepository.findByBoutiqueAndCategorie(personnelBoutique.getBoutique(), categorie)
                        .orElseThrow(() -> new IllegalArgumentException("Categorie not associated with the personnel's boutique"));
                return boutiqueCategorie.toDTO();
            }

            @Override
            public List<CategorieDTO> getAllCategories(Long personnelId) {
                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findByIdWithBoutiqueCategories(personnelId);
                return personnelBoutique.getBoutique().getBoutiqueCategories().stream()
                        .map(BoutiqueCategorie::toDTO)
                        .collect(Collectors.toList());
            }

            // File: src/main/java/com/example/demo/service/PersonnelBoutique/PBCategorieServiceImpl.java
            @Override
            public void createCategorie(Long personnelId, Long categorieId) {
                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                        .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                Categorie categorie = categorieRepository.findById(categorieId)
                        .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + categorieId));

                BoutiqueCategorie boutiqueCategorie = new BoutiqueCategorie();
                boutiqueCategorie.setBoutique(personnelBoutique.getBoutique());
                boutiqueCategorie.setCategorie(categorie);
                boutiqueCategorieRepository.save(boutiqueCategorie);
            }

            @Override
            public CategorieDTO updateCategorie(Long personnelId, Long categorieId, CategorieDTO categorieDTO) {
                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                        .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                Categorie categorie = categorieRepository.findById(categorieId)
                        .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + categorieId));
                BoutiqueCategorie boutiqueCategorie = boutiqueCategorieRepository.findByBoutiqueAndCategorie(personnelBoutique.getBoutique(), categorie)
                        .orElseThrow(() -> new IllegalArgumentException("Categorie not associated with the personnel's boutique"));
                categorie.setNom(categorieDTO.getNom());
                categorieRepository.save(categorie);
                return boutiqueCategorie.toDTO();
            }

            @Override
            public void deleteCategorie(Long personnelId, Long categorieId) {
                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                        .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                Categorie categorie = categorieRepository.findById(categorieId)
                        .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + categorieId));
                BoutiqueCategorie boutiqueCategorie = boutiqueCategorieRepository.findByBoutiqueAndCategorie(personnelBoutique.getBoutique(), categorie)
                        .orElseThrow(() -> new IllegalArgumentException("Categorie not associated with the personnel's boutique"));
                boutiqueCategorieRepository.delete(boutiqueCategorie);
            }
        }