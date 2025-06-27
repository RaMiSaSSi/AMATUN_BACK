package com.example.demo.service.Admin;

        import com.example.demo.dto.ProduitDTO;
        import com.example.demo.model.Produit;
        import com.example.demo.repository.ProduitRepository;
        import com.example.demo.repository.CategorieRepository;
        import com.example.demo.repository.SousCategorieRepository;
        import com.example.demo.repository.BoutiqueRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.stream.Collectors;

        @Service
        public class ProduitServiceImpl implements ProduitService {
            @Autowired
            private ProduitRepository produitRepository;

            @Autowired
            private CategorieRepository categorieRepository;

            @Autowired
            private SousCategorieRepository sousCategorieRepository;

            @Autowired
            private BoutiqueRepository boutiqueRepository;

            @Override
            public ProduitDTO getProduitById(Long id) {
                Produit produit = produitRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Produit not found with id: " + id));
                return toDTO(produit);
            }

            @Override
            public List<ProduitDTO> getAllProduits() {
                return produitRepository.findAll().stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList());
            }

            @Override
            public ProduitDTO createProduit(ProduitDTO produitDTO) {
                Produit produit = toEntity(produitDTO);
                produit.setImagePath(produitDTO.getImagePath());
                Produit savedProduit = produitRepository.save(produit);
                return toDTO(savedProduit);
            }

            @Override
            public ProduitDTO updateProduit(Long produitId, ProduitDTO produitDTO) {
                Produit produit = produitRepository.findById(produitId)
                        .orElseThrow(() -> new IllegalArgumentException("Produit not found with id: " + produitId));

                // Update only the fields that are provided
                if (produitDTO.getNom() != null) {
                    produit.setNom(produitDTO.getNom());
                }
                if (produitDTO.getImagePath() != null) {
                    produit.setImagePath(produitDTO.getImagePath());
                }
                if (produitDTO.getDescription() != null) {
                    produit.setDescription(produitDTO.getDescription());
                }
                if (produitDTO.getPrix() != 0) {
                    produit.setPrix(produitDTO.getPrix());
                }
                if (produitDTO.getQuantite() != 0) {
                    produit.setQuantite(produitDTO.getQuantite());
                }
                produit.setPromo(produitDTO.isPromo()); // Boolean fields can be directly updated
                if (produitDTO.getPromotionPercentage() != 0.0) {
                    produit.setPromotionPercentage(produitDTO.getPromotionPercentage());
                }
                if (produitDTO.getDuree() != 0.0) {
                    produit.setDuree(produitDTO.getDuree());
                }
                if (produitDTO.getStartDate() != null) {
                    produit.setStartDate(produitDTO.getStartDate());
                }
                if (produitDTO.getBoutiqueId() != null) {
                    produit.setBoutique(boutiqueRepository.findById(produitDTO.getBoutiqueId()).orElse(null));
                }
                if (produitDTO.getCategorieId() != null) {
                    produit.setCategorie(categorieRepository.findById(produitDTO.getCategorieId()).orElse(null));
                }
                if (produitDTO.getSousCategorieId() != null) {
                    produit.setSousCategorie(sousCategorieRepository.findById(produitDTO.getSousCategorieId()).orElse(null));
                }

                Produit updatedProduit = produitRepository.save(produit);
                return toDTO(updatedProduit);
            }

            @Override
            public void deleteProduit(Long id) {
                Produit produit = produitRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Produit not found with id: " + id));
                produitRepository.delete(produit);
            }

            private ProduitDTO toDTO(Produit produit) {
                ProduitDTO dto = new ProduitDTO();
                dto.setId(produit.getId());
                dto.setNom(produit.getNom());
                dto.setImagePath(produit.getImagePath());
                dto.setDescription(produit.getDescription());
                dto.setPrix(produit.getPrix());
                dto.setQuantite(produit.getQuantite());
                dto.setPromo(produit.isPromo());
                dto.setPromotionPercentage(produit.getPromotionPercentage());
                dto.setDuree(produit.getDuree());
                dto.setStartDate(produit.getStartDate());
                if (produit.getCategorie() != null) {
                    dto.setCategorieId(produit.getCategorie().getId());
                }
                if (produit.getSousCategorie() != null) {
                    dto.setSousCategorieId(produit.getSousCategorie().getId());
                }
                if (produit.getBoutique() != null) {
                    dto.setBoutiqueId(produit.getBoutique().getId());
                }
                return dto;
            }

            private Produit toEntity(ProduitDTO dto) {
                Produit produit = new Produit();
                produit.setNom(dto.getNom());
                /*produit.setImage(dto.getImage());*/
                produit.setImagePath(dto.getImagePath());
                produit.setDescription(dto.getDescription());
                produit.setPrix(dto.getPrix());
                produit.setQuantite(dto.getQuantite());
                produit.setPromo(dto.isPromo());
                produit.setPromotionPercentage(dto.getPromotionPercentage());
                produit.setDuree(dto.getDuree());
                produit.setStartDate(dto.getStartDate());
                produit.setCategorie(dto.getCategorieId() != null ? categorieRepository.findById(dto.getCategorieId()).orElse(null) : null);
                produit.setSousCategorie(dto.getSousCategorieId() != null ? sousCategorieRepository.findById(dto.getSousCategorieId()).orElse(null) : null);
                produit.setBoutique(dto.getBoutiqueId() != null ? boutiqueRepository.findById(dto.getBoutiqueId()).orElse(null) : null);
                return produit;
            }
            
        }