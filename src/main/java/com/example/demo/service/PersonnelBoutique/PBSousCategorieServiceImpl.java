package com.example.demo.service.PersonnelBoutique;

                                                import com.example.demo.dto.SousCategorieDTO;
                                                import com.example.demo.model.BoutiqueCategorie;
                                                import com.example.demo.model.Categorie;
                                                import com.example.demo.model.PersonnelBoutique;
                                                import com.example.demo.model.SousCategorie;
                                                import com.example.demo.repository.BoutiqueCategorieRepository;
                                                import com.example.demo.repository.PersonnelBoutiqueRepository;
                                                import com.example.demo.repository.SousCategorieRepository;
                                                import org.springframework.beans.factory.annotation.Autowired;
                                                import org.springframework.stereotype.Service;
                                                import java.util.List;
                                                import java.util.stream.Collectors;

                                                @Service
                                                public class PBSousCategorieServiceImpl implements PBSousCategorieService {
                                                    @Autowired
                                                    private PersonnelBoutiqueRepository personnelBoutiqueRepository;
                                                    @Autowired
                                                    private SousCategorieRepository sousCategorieRepository;
                                                    @Autowired
                                                    private BoutiqueCategorieRepository boutiqueCategorieRepository;

                                                    @Override
                                                    public SousCategorieDTO getSousCategorieById(Long personnelId, Long sousCategorieId) {
                                                        PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                                                .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                                        SousCategorie sousCategorie = sousCategorieRepository.findById(sousCategorieId)
                                                                .orElseThrow(() -> new IllegalArgumentException("SousCategorie not found with id: " + sousCategorieId));
                                                        Categorie categorie = sousCategorie.getCategorie();
                                                        BoutiqueCategorie boutiqueCategorie = boutiqueCategorieRepository.findByCategorie(categorie)
                                                                .orElseThrow(() -> new IllegalArgumentException("Categorie not associated with any boutique"));
                                                        if (!personnelBoutique.getBoutique().getBoutiqueCategories().contains(boutiqueCategorie)) {
                                                            throw new IllegalArgumentException("SousCategorie not associated with the personnel's boutique");
                                                        }
                                                        SousCategorieDTO dto = sousCategorie.toDTO();
                                                        dto.setBoutiqueId(personnelBoutique.getBoutique().getId());
                                                        return dto;
                                                    }

                                                    @Override
                                                    public List<SousCategorieDTO> getAllSousCategories(Long personnelId) {
                                                        PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                                                .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                                        return personnelBoutique.getBoutique().getBoutiqueCategories().stream()
                                                                .flatMap(bc -> bc.getCategorie().getSousCategories().stream())
                                                                .map(sousCategorie -> {
                                                                    SousCategorieDTO dto = sousCategorie.toDTO();
                                                                    dto.setBoutiqueId(personnelBoutique.getBoutique().getId());
                                                                    return dto;
                                                                })
                                                                .collect(Collectors.toList());
                                                    }

                                                    @Override
                                                    public SousCategorieDTO createSousCategorie(Long personnelId, Long categorieId, SousCategorie sousCategorie) {
                                                        PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                                                .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                                        BoutiqueCategorie boutiqueCategorie = boutiqueCategorieRepository.findByCategorieId(categorieId)
                                                                .orElseThrow(() -> new IllegalArgumentException("Categorie not found with id: " + categorieId));
                                                        Categorie categorie = boutiqueCategorie.getCategorie();
                                                        sousCategorie.setCategorie(categorie);
                                                        SousCategorie createdSousCategorie = sousCategorieRepository.save(sousCategorie);
                                                        SousCategorieDTO dto = createdSousCategorie.toDTO();
                                                        dto.setBoutiqueId(personnelBoutique.getBoutique().getId());
                                                        return dto;
                                                    }

                                                    @Override
                                                    public SousCategorieDTO updateSousCategorie(Long personnelId, Long sousCategorieId, SousCategorie sousCategorie) {
                                                        PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                                                .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                                        SousCategorie existingSousCategorie = sousCategorieRepository.findById(sousCategorieId)
                                                                .orElseThrow(() -> new IllegalArgumentException("SousCategorie not found with id: " + sousCategorieId));
                                                        Categorie categorie = existingSousCategorie.getCategorie();
                                                        BoutiqueCategorie boutiqueCategorie = boutiqueCategorieRepository.findByCategorie(categorie)
                                                                .orElseThrow(() -> new IllegalArgumentException("Categorie not associated with any boutique"));
                                                        if (!personnelBoutique.getBoutique().getBoutiqueCategories().contains(boutiqueCategorie)) {
                                                            throw new IllegalArgumentException("SousCategorie not associated with the personnel's boutique");
                                                        }
                                                        existingSousCategorie.setNom(sousCategorie.getNom());
                                                        existingSousCategorie.setImage(sousCategorie.getImage());
                                                        SousCategorie updatedSousCategorie = sousCategorieRepository.save(existingSousCategorie);
                                                        SousCategorieDTO dto = updatedSousCategorie.toDTO();
                                                        dto.setBoutiqueId(personnelBoutique.getBoutique().getId());
                                                        return dto;
                                                    }

                                                    @Override
                                                    public void deleteSousCategorie(Long personnelId, Long sousCategorieId) {
                                                        PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                                                .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                                        SousCategorie sousCategorie = sousCategorieRepository.findById(sousCategorieId)
                                                                .orElseThrow(() -> new IllegalArgumentException("SousCategorie not found with id: " + sousCategorieId));
                                                        Categorie categorie = sousCategorie.getCategorie();
                                                        BoutiqueCategorie boutiqueCategorie = boutiqueCategorieRepository.findByCategorie(categorie)
                                                                .orElseThrow(() -> new IllegalArgumentException("Categorie not associated with any boutique"));
                                                        if (!personnelBoutique.getBoutique().getBoutiqueCategories().contains(boutiqueCategorie)) {
                                                            throw new IllegalArgumentException("SousCategorie not associated with the personnel's boutique");
                                                        }
                                                        sousCategorieRepository.delete(sousCategorie);
                                                    }

                                                    @Override
                                                    public List<SousCategorieDTO> getSousCategoriesByCategorie(Long personnelId, Long categorieId) {
                                                        PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                                                .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                                        BoutiqueCategorie boutiqueCategorie = personnelBoutique.getBoutique().getBoutiqueCategories().stream()
                                                                .filter(bc -> bc.getCategorie().getId() == categorieId)
                                                                .findFirst()
                                                                .orElseThrow(() -> new IllegalArgumentException("Categorie not associated with the personnel's boutique"));
                                                        return boutiqueCategorie.getCategorie().getSousCategories().stream()
                                                                .map(sousCategorie -> {
                                                                    SousCategorieDTO dto = sousCategorie.toDTO();
                                                                    dto.setBoutiqueId(personnelBoutique.getBoutique().getId());
                                                                    return dto;
                                                                })
                                                                .collect(Collectors.toList());
                                                    }
                                                }