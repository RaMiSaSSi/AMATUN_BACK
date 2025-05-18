package com.example.demo.service.PersonnelBoutique;

import com.example.demo.dto.UtilisateurInscritDTO;

import java.util.List;

public interface PBFollowersService {
    List<UtilisateurInscritDTO> getFollowersByPersonnelBoutique(Long personnelBoutiqueId);
}