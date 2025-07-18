package com.example.demo.service.Admin;

                        import com.example.demo.dto.CommandeDTO;
                        import com.example.demo.model.*;
                        import com.example.demo.repository.AdresseRepository;
                        import com.example.demo.repository.CommandeRepository;
                        import com.example.demo.repository.ProduitRepository;
                        import com.example.demo.repository.UtilisateurInscritRepository;
                        import org.springframework.beans.factory.annotation.Autowired;
                        import org.springframework.data.domain.Page;
                        import org.springframework.data.domain.Pageable;
                        import org.springframework.stereotype.Service;

                        import java.util.List;
                        import java.util.stream.Collectors;

                        @Service
                        public class CommandeServiceImpl implements CommandeService {

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
                            public CommandeDTO getCommandeById(Long id) {
                                Commande commande = commandeRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("Commande not found"));
                                return commande.toDTO();
                            }

                            @Override
                            public List<CommandeDTO> getAllCommandes() {
                                return commandeRepository.findAll().stream().map(Commande::toDTO).collect(Collectors.toList());
                            }
                            @Override
                            public CommandeDTO updateCommande(Long id, CommandeDTO commandeDTO) {
                                Commande commande = commandeRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("Commande not found"));

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

                                // Check if the status is updated to CONFIRMED
                                if (commandeDTO.getStatut() == Statut.CONFIRMED) {
                                    commande.getProduits().forEach(produitCommande -> {
                                        Produit produit = produitCommande.getProduit();
                                        int newQuantity = produit.getQuantite() - produitCommande.getQuantite();

                                        // Ensure the quantity does not go below zero
                                        if (newQuantity < 0) {
                                            throw new IllegalArgumentException("Insufficient stock for product: " + produit.getNom());
                                        }

                                        produit.setQuantite(newQuantity);
                                        produitRepository.save(produit);
                                    });
                                }

                                commande.setStatut(commandeDTO.getStatut());
                                commandeRepository.save(commande);

                                return commande.toDTO();
                            }
                            @Override
                            public CommandeDTO changeStatut(Long id, Statut statut) {
                                Commande commande = commandeRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("Commande not found"));

                                // Update the status of the command
                                commande.setStatut(statut);

                                // If the status is changed to CONFIRMED, reduce the product quantities
                                if (statut == Statut.CONFIRMED) {
                                    commande.getProduits().forEach(produitCommande -> {
                                        Produit produit = produitCommande.getProduit();
                                        int newQuantity = produit.getQuantite() - produitCommande.getQuantite();

                                        // Ensure the quantity does not go below zero
                                        if (newQuantity < 0) {
                                            throw new IllegalArgumentException("Insufficient stock for product: " + produit.getNom());
                                        }

                                        produit.setQuantite(newQuantity);
                                        produitRepository.save(produit);
                                    });
                                }

                                commandeRepository.save(commande);

                                return commande.toDTO();
                            }
                            @Override
                            public Page<CommandeDTO> getAllCommandesPaggination(Pageable pageable) {
                                return commandeRepository.findAll(pageable)
                                        .map(Commande::toDTO);
                            }
                        }