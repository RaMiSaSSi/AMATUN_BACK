// File: src/main/java/com/example/demo/service/impl/FavouriteServiceImpl.java
package com.example.demo.service.User;

import com.example.demo.dto.FavouriteDTO;
import com.example.demo.model.Favourite;
import com.example.demo.model.Produit;
import com.example.demo.model.UtilisateurInscrit;
import com.example.demo.repository.FavouriteRepository;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.repository.UtilisateurInscritRepository;
import com.example.demo.service.User.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private UtilisateurInscritRepository utilisateurInscritRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public void addFavourite(Long utilisateurId, Long produitId) {
        // Check if the favourite already exists
        if (favouriteRepository.existsByUtilisateurIdAndProduitId(utilisateurId, produitId)) {
            throw new IllegalArgumentException("This product is already in the user's favorites.");
        }

        UtilisateurInscrit utilisateur = utilisateurInscritRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Favourite favourite = new Favourite();
        favourite.setUtilisateur(utilisateur);
        favourite.setProduit(produit);

        favouriteRepository.save(favourite);
    }

    @Override
    public void removeFavourite(Long utilisateurId, Long produitId) {
        favouriteRepository.deleteByUtilisateurIdAndProduitId(utilisateurId, produitId);
    }

    @Override
    public List<FavouriteDTO> getFavourites(Long utilisateurId) {
        return favouriteRepository.findByUtilisateurId(utilisateurId).stream()
                .map(favourite -> new FavouriteDTO(
                        favourite.getId(),
                        favourite.getUtilisateur().getId(),
                        favourite.getProduit().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean userLikeProduit(Long utilisateurId, Long produitId) {
        return favouriteRepository.existsByUtilisateurIdAndProduitId(utilisateurId, produitId);
    }
}