// File: src/main/java/com/example/demo/service/User/UCommandeServiceImpl.java

                    package com.example.demo.service.User;

                    import com.example.demo.dto.CommandeDTO;
                    import com.example.demo.model.*;
                    import com.example.demo.repository.AdresseRepository;
                    import com.example.demo.repository.CommandeRepository;
                    import com.example.demo.repository.ProduitRepository;
                    import com.example.demo.repository.NotificationRepository;
                    import com.example.demo.repository.UtilisateurInscritRepository;
                    import org.springframework.beans.factory.annotation.Autowired;
                    import org.springframework.messaging.simp.SimpMessagingTemplate;
                    import org.springframework.stereotype.Service;
                    import org.springframework.transaction.annotation.Transactional;

                    import java.util.List;
                    import java.util.ArrayList;
                    import java.util.stream.Collectors;

                    @Service
                    @Transactional

                    public class UCommandeServiceImpl implements UCommandeService {
                        @Autowired
                        private SimpMessagingTemplate messagingTemplate;
                        @Autowired
                        private CommandeRepository commandeRepository;
                        @Autowired
                        private NotificationRepository notificationRepository;
                        @Autowired
                        private ProduitRepository produitRepository;

                        @Autowired
                        private UtilisateurInscritRepository utilisateurInscritRepository;

                        @Autowired
                        private AdresseRepository adresseRepository;

                        @Override
                        public CommandeDTO createCommande(CommandeDTO commandeDTO) {
                            Commande commande = new Commande();

                            if (commandeDTO.getClientId() != null) {
                                UtilisateurInscrit client = utilisateurInscritRepository.findById(commandeDTO.getClientId())
                                        .orElseThrow(() -> new IllegalArgumentException("Client not found"));
                                commande.setClient(client);
                                commande.setNom(client.getNom());
                                commande.setPrenom(client.getPrenom());
                                commande.setAdresse(client.getAdresseLivraison());
                                commande.setNumTel(client.getTelephone());
                            } else {
                                commande.setNom(commandeDTO.getNom());
                                commande.setPrenom(commandeDTO.getPrenom());
                                Adresse adresse = commandeDTO.getAdresse().toEntity();
                                adresse = adresseRepository.save(adresse);
                                commande.setAdresse(adresse);
                                commande.setNumTel(commandeDTO.getNumTel());
                            }

                            commande.setPrixTotalSansLivraison(commandeDTO.getPrixTotalSansLivraison());
                            commande.setPrixTotalAvecLivraison(commandeDTO.getPrixTotalAvecLivraison());

                            List<CommandeProduit> produits = commandeDTO.getProduits().stream().map(dto -> {
                                CommandeProduit cp = new CommandeProduit();
                                cp.setCommande(commande);
                                cp.setProduit(produitRepository.findById(dto.getProduitId())
                                        .orElseThrow(() -> new IllegalArgumentException("Produit not found")));
                                cp.setQuantite(dto.getQuantite());
                                return cp;
                            }).collect(Collectors.toList());

                            commande.setProduits(produits);
                            commandeRepository.save(commande);

                            // Notify boutiques
                            commande.getProduits().forEach(cp -> {
                                Boutique boutique = cp.getProduit().getBoutique();

                                // Create a defensive copy of the personnelBoutiques collection
                                List<PersonnelBoutique> personnelList = new ArrayList<>(boutique.getPersonnelBoutiques());

                                personnelList.forEach(personnel -> {
                                    String message = "Nouvelle commande pour la boutique: " + boutique.getNom();
                                    Notification notification = new Notification(commande.getId(), message, personnel.getId());
                                    notificationRepository.save(notification);

                                    // Send real-time notification to the personnel
                                    messagingTemplate.convertAndSend("/topic/personnel-" + personnel.getId(), notification);
                                });
                            });

                            return commande.toDTO();
                        }
                        @Override
                        public List<Long> getProductIdsByUserAndConfirmedStatus(Long userId) {
                            return commandeRepository.findByClientIdAndStatut(userId, Statut.CONFIRMED)
                                    .stream()
                                    .flatMap(commande -> commande.getProduits().stream())
                                    .map(commandeProduit -> commandeProduit.getProduit().getId())
                                    .collect(Collectors.toList());
                        }
                    }