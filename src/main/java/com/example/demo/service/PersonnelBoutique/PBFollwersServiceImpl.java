package com.example.demo.service.PersonnelBoutique;

import com.example.demo.dto.UtilisateurInscritDTO;
import com.example.demo.model.Boutique;
import com.example.demo.model.Follow;
import com.example.demo.model.PersonnelBoutique;
import com.example.demo.model.UtilisateurInscrit;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.PersonnelBoutiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PBFollwersServiceImpl implements PBFollowersService {

    @Autowired
    private PersonnelBoutiqueRepository personnelBoutiqueRepository;
    @Autowired
    private FollowRepository followRepository;

    @Override
    public List<UtilisateurInscritDTO> getFollowersByPersonnelBoutique(Long personnelBoutiqueId) {
        PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelBoutiqueId)
                .orElseThrow(() -> new IllegalArgumentException("PersonnelBoutique not found with id: " + personnelBoutiqueId));
        Boutique boutique = personnelBoutique.getBoutique();

        List<Follow> follows = followRepository.findByBoutiqueId(boutique.getId());
        return follows.stream()
                .map(follow -> {
                    UtilisateurInscrit user = follow.getUtilisateur();
                    UtilisateurInscritDTO dto = new UtilisateurInscritDTO();
                    dto.setNom(user.getNom());
                    dto.setPrenom(user.getPrenom());
                    dto.setEmail(user.getEmail());
                    dto.setTelephone(user.getTelephone());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}