// File: src/main/java/com/example/demo/service/PersonnelBoutique/PBCommandeServiceImpl.java

    package com.example.demo.service.PersonnelBoutique;

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
    public class PBCommandeServiceImpl implements PBCommandeService {

        @Autowired
        private CommandeRepository commandeRepository;

        @Autowired
        private UtilisateurInscritRepository utilisateurInscritRepository;

        @Autowired
        private AdresseRepository adresseRepository;
        @Autowired
        private ProduitRepository produitRepository;


        @Override
        public List<CommandeDTO> getCommandesByBoutiqueId(Long boutiqueId) {
            List<Commande> commandes = commandeRepository.findByBoutiqueId(boutiqueId);
            return commandes.stream().map(Commande::toDTO).collect(Collectors.toList());
        }
        @Override
        public CommandeDTO getCommandeById(Long id) {
            Commande commande = commandeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Commande not found"));
            return commande.toDTO();
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

            // Check if the status is changing from PENDING to CONFIRMED
            if (commande.getStatut() == Statut.PENDING && commandeDTO.getStatut() == Statut.CONFIRMED) {
                commande.getProduits().forEach(produitCommande -> {
                    Produit produit = produitCommande.getProduit();
                    produit.setQuantite(produit.getQuantite() - produitCommande.getQuantite());
                    produitRepository.save(produit);
                });
            }

            commande.setStatut(commandeDTO.getStatut());
            commande.setDate(commandeDTO.getDate());

            commandeRepository.save(commande);

            return commande.toDTO();
        }

        @Override
        public CommandeDTO changeStatut(Long id, Statut statut) {
            Commande commande = commandeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Commande not found"));

            commande.setStatut(statut);

            if (statut == Statut.CONFIRMED) {
                commande.getProduits().forEach(produitCommande -> {
                    Produit produit = produitCommande.getProduit();
                    produit.setQuantite(produit.getQuantite() - produitCommande.getQuantite());
                    produitRepository.save(produit);
                });
            }

            commandeRepository.save(commande);

            return commande.toDTO();
        }
    }