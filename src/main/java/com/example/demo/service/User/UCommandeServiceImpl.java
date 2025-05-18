// File: src/main/java/com/example/demo/service/User/UCommandeServiceImpl.java

                    package com.example.demo.service.User;

                    import com.example.demo.dto.CommandeDTO;
                    import com.example.demo.model.*;
                    import com.example.demo.repository.AdresseRepository;
                    import com.example.demo.repository.CommandeRepository;
                    import com.example.demo.repository.ProduitRepository;
                    import com.example.demo.repository.UtilisateurInscritRepository;
                    import org.springframework.beans.factory.annotation.Autowired;
                    import org.springframework.stereotype.Service;

                    import java.util.List;
                    import java.util.stream.Collectors;

                    @Service
                    public class UCommandeServiceImpl implements UCommandeService {

                        @Autowired
                        private CommandeRepository commandeRepository;

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
                                // Utilisateur inscrit
                                UtilisateurInscrit client = utilisateurInscritRepository.findById(commandeDTO.getClientId())
                                        .orElseThrow(() -> new IllegalArgumentException("Client not found"));
                                commande.setClient(client);
                                commande.setNom(client.getNom());
                                commande.setPrenom(client.getPrenom());
                                commande.setAdresse(client.getAdresseLivraison());
                                commande.setNumTel(client.getTelephone());
                            } else {
                                // Utilisateur non inscrit
                                commande.setNom(commandeDTO.getNom());
                                commande.setPrenom(commandeDTO.getPrenom());
                                Adresse adresse = commandeDTO.getAdresse().toEntity();
                                adresse = adresseRepository.save(adresse); // Save the Adresse entity first
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