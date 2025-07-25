// File: src/main/java/com/example/demo/service/User/UBoutiqueServiceImpl.java
                            package com.example.demo.service.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
                            import com.example.demo.dto.AdresseDTO;
                            import com.example.demo.dto.BoutiqueDTO;
                            import com.example.demo.model.Boutique;
                            import com.example.demo.model.Follow;
                            import com.example.demo.model.UtilisateurInscrit;
                            import com.example.demo.repository.BoutiqueRepository;
                            import com.example.demo.repository.FollowRepository;
                            import com.example.demo.repository.UtilisateurInscritRepository;
                            import org.springframework.beans.factory.annotation.Autowired;
                            import org.springframework.stereotype.Service;

                            import java.util.ArrayList;
                            import java.util.List;
                            import java.util.stream.Collectors;

                            @Service
                            public class UBoutiqueServiceImpl implements UBoutiqueService {

                                @Autowired
                                private BoutiqueRepository boutiqueRepository;
                                @Autowired
                                private UtilisateurInscritRepository utilisateurInscritRepository;
                                @Autowired
                                private FollowRepository followRepository;

                                @Override
                                public Page<BoutiqueDTO> getAllBoutiques(Pageable pageable) {
                                    return boutiqueRepository.findAll(pageable)
                                            .map(Boutique::getDTO);
                                }

                                @Override
                                public BoutiqueDTO getBoutiqueById(Long id) {
                                    Boutique boutique = boutiqueRepository.findById(id)
                                            .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + id));
                                    boutique.setViews(boutique.getViews() + 1);
                                    boutiqueRepository.save(boutique);
                                    return boutique.getDTO();
                                }

                                @Override
                                public AdresseDTO getBoutiqueAdresseById(Long id) {
                                    Boutique boutique = boutiqueRepository.findById(id)
                                            .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + id));
                                    return boutique.getAdresse().toDTO();
                                }

                                @Override
                                public void followBoutique(Long utilisateurId, Long boutiqueId) {
                                    UtilisateurInscrit utilisateur = utilisateurInscritRepository.findById(utilisateurId)
                                            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + utilisateurId));
                                    Boutique boutique = boutiqueRepository.findById(boutiqueId)
                                            .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + boutiqueId));

                                    boolean alreadyFollowed = followRepository.existsByUtilisateurAndBoutique(utilisateur, boutique);
                                    if (alreadyFollowed) {
                                        throw new IllegalArgumentException("User already follows this boutique");
                                    }

                                    Follow follow = new Follow();
                                    follow.setUtilisateur(utilisateur);
                                    follow.setBoutique(boutique);

                                    followRepository.save(follow);
                                }
                                @Override
                                public void unfollowBoutique(Long utilisateurId, Long boutiqueId) {
                                    UtilisateurInscrit utilisateur = utilisateurInscritRepository.findById(utilisateurId)
                                            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + utilisateurId));
                                    Boutique boutique = boutiqueRepository.findById(boutiqueId)
                                            .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + boutiqueId));

                                    Follow follow = followRepository.findByUtilisateurAndBoutique(utilisateur, boutique)
                                            .orElseThrow(() -> new IllegalArgumentException("Follow relationship not found"));

                                    followRepository.delete(follow);
                                }
                                @Override
                                public List<BoutiqueDTO> searchBoutiques(String keyword) {
                                    List<Boutique> boutiques = boutiqueRepository.searchByName(keyword);
                                    return boutiques.stream().map(Boutique::getDTO).collect(Collectors.toList());
                                }
                                @Override
                                public int getFollowersCount(Long boutiqueId) {
                                    Boutique boutique = boutiqueRepository.findById(boutiqueId)
                                            .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + boutiqueId));
                                    return followRepository.countByBoutique(boutique);
                                }
                                @Override
                                public List<BoutiqueDTO> getBoutiquesByCategoryShopId(Long categoryShopId) {
                                    List<Boutique> boutiques = boutiqueRepository.findByCategoryShopId(categoryShopId);
                                    return boutiques.stream().map(Boutique::getDTO).collect(Collectors.toList());
                                }
                                @Override
                                public int getBoutiqueCountByCategoryShopId(Long categoryShopId) {
                                    return boutiqueRepository.countByCategoryShopId(categoryShopId);
                                }
                                @Override
                                public Page<BoutiqueDTO> getBoutiquesWithMostFollowers(Pageable pageable) {
                                    Page<Boutique> boutiques = boutiqueRepository.findBoutiquesWithMostFollowers(pageable);
                                    return boutiques.map(Boutique::getDTO);
                                }
                                // Java
                                @Override
                                public int getProductCountByBoutiqueId(Long boutiqueId) {
                                    return boutiqueRepository.countProductsByBoutiqueId(boutiqueId);
                                }
                                @Override
                                public boolean isBoutiqueFollowed(Long utilisateurId, Long boutiqueId) {
                                    UtilisateurInscrit utilisateur = utilisateurInscritRepository.findById(utilisateurId)
                                            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + utilisateurId));
                                    Boutique boutique = boutiqueRepository.findById(boutiqueId)
                                            .orElseThrow(() -> new IllegalArgumentException("Boutique not found with id: " + boutiqueId));
                                    return followRepository.existsByUtilisateurAndBoutique(utilisateur, boutique);
                                }
                                @Override
                                public List<BoutiqueDTO> getBoutiquesWithPromotionalProducts() {
                                    List<Boutique> boutiques = boutiqueRepository.findBoutiquesWithPromotionalProducts();
                                    return boutiques.stream().map(Boutique::getDTO).collect(Collectors.toList());
                                }
                                @Override
                                public List<BoutiqueDTO> getBoutiquesByAutoMotoCategory() {
                                    List<Boutique> boutiques = boutiqueRepository.findByCategoryShopName("Auto et Moto");
                                    return boutiques.stream().map(Boutique::getDTO).collect(Collectors.toList());
                                }
                            }