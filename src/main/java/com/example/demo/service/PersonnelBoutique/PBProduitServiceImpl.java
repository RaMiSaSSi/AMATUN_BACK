// File: src/main/java/com/example/demo/service/PersonnelBoutique/PBProduitServiceImpl.java
        package com.example.demo.service.PersonnelBoutique;

        import com.example.demo.dto.ProduitDTO;
        import com.example.demo.model.Follow;
        import com.example.demo.model.PersonnelBoutique;
        import com.example.demo.model.Produit;
        import com.example.demo.repository.*;
        import com.example.demo.service.Auth.EmailService;
        import jakarta.mail.MessagingException;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.stream.Collectors;

        @Service
        public class PBProduitServiceImpl implements PBProduitService {
            @Autowired
            private ProduitRepository produitRepository;

            @Autowired
            private PersonnelBoutiqueRepository personnelBoutiqueRepository;

            @Autowired
            private CategorieRepository categorieRepository;

            @Autowired
            private SousCategorieRepository sousCategorieRepository;

            @Autowired
            private FollowRepository followRepository;

            @Autowired
            private EmailService emailService;

            @Override
            public ProduitDTO getProduitById(Long id) {
                Produit produit = produitRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Produit not found with id: " + id));
                return toDTO(produit);
            }

            @Override
            public List<ProduitDTO> getAllProduitsForPersonnelBoutique(Long personnelBoutiqueId) {
                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelBoutiqueId)
                        .orElseThrow(() -> new IllegalArgumentException("PersonnelBoutique not found with id: " + personnelBoutiqueId));
                return produitRepository.findAll().stream()
                        .filter(produit -> Long.valueOf(produit.getBoutique().getId()).equals(personnelBoutique.getBoutique().getId()))
                        .map(this::toDTO)
                        .collect(Collectors.toList());
            }

            @Override
            public ProduitDTO createProduitForPersonnelBoutique(Long personnelBoutiqueId, ProduitDTO produitDTO) {
                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelBoutiqueId)
                        .orElseThrow(() -> new IllegalArgumentException("PersonnelBoutique not found with id: " + personnelBoutiqueId));
                Produit produit = toEntity(produitDTO);
                produit.setBoutique(personnelBoutique.getBoutique());
                Produit savedProduit = produitRepository.save(produit);

                // Send email to followers
                List<Follow> follows = followRepository.findByBoutiqueId(personnelBoutique.getBoutique().getId());
                List<String> emails = follows.stream()
                        .map(follow -> follow.getUtilisateur().getEmail())
                        .collect(Collectors.toList());

                String subject = "New Product Added";
                String text = "A new product has been added to the boutique you follow: " + produit.getNom() +
                        " in the boutique: " + personnelBoutique.getBoutique().getNom();

                for (String email : emails) {
                    try {
                        emailService.sendEmail(email, subject, text);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }

                return toDTO(savedProduit);
            }

            @Override
            public ProduitDTO updateProduitForPersonnelBoutique(Long personnelBoutiqueId, Long produitId, ProduitDTO produitDTO) {
                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelBoutiqueId)
                        .orElseThrow(() -> new IllegalArgumentException("PersonnelBoutique not found with id: " + personnelBoutiqueId));
                Produit produit = produitRepository.findById(produitId)
                        .orElseThrow(() -> new IllegalArgumentException("Produit not found with id: " + produitId));
                produit.setNom(produitDTO.getNom());
                /*produit.setImage(produitDTO.getImage());*/
                produit.setDescription(produitDTO.getDescription());
                produit.setPrix(produitDTO.getPrix());
                produit.setQuantite(produitDTO.getQuantite());
                produit.setPromo(produitDTO.isPromo());
                produit.setPromotionPercentage(produitDTO.getPromotionPercentage());
                produit.setDuree(produitDTO.getDuree());
                produit.setStartDate(produitDTO.getStartDate());
                produit.setCategorie(produitDTO.getCategorieId() != null ? categorieRepository.findById(produitDTO.getCategorieId()).orElse(null) : null);
                produit.setSousCategorie(produitDTO.getSousCategorieId() != null ? sousCategorieRepository.findById(produitDTO.getSousCategorieId()).orElse(null) : null);
                produit.setBoutique(personnelBoutique.getBoutique());
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
                /*dto.setImage(produit.getImage());*/
                dto.setDescription(produit.getDescription());
                dto.setPrix(produit.getPrix());
                dto.setQuantite(produit.getQuantite());
                dto.setPromotionPercentage(produit.getPromotionPercentage());
                dto.setDuree(produit.getDuree());
                dto.setStartDate(produit.getStartDate());
                dto.setPromo(produit.isPromo());
                dto.setCategorieId(produit.getCategorie() != null ? produit.getCategorie().getId() : null);
                dto.setSousCategorieId(produit.getSousCategorie() != null ? produit.getSousCategorie().getId() : null);
                dto.setBoutiqueId(produit.getBoutique() != null ? produit.getBoutique().getId() : null);
                return dto;
            }

            private Produit toEntity(ProduitDTO dto) {
                Produit produit = new Produit();
                produit.setNom(dto.getNom());
                /*produit.setImage(dto.getImage());*/
                produit.setDescription(dto.getDescription());
                produit.setPrix(dto.getPrix());
                produit.setQuantite(dto.getQuantite());
                produit.setPromo(dto.isPromo());
                produit.setPromotionPercentage(dto.getPromotionPercentage());
                produit.setDuree(dto.getDuree());
                produit.setStartDate(dto.getStartDate());
                produit.setCategorie(dto.getCategorieId() != null ? categorieRepository.findById(dto.getCategorieId()).orElse(null) : null);
                produit.setSousCategorie(dto.getSousCategorieId() != null ? sousCategorieRepository.findById(dto.getSousCategorieId()).orElse(null) : null);
                return produit;
            }
        }