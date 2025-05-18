// service/UtilisateurInscritService.java
package com.example.demo.service.Admin;

import com.example.demo.dto.UtilisateurInscritDTO;
import com.example.demo.model.UtilisateurInscrit;

import java.util.List;

public interface UtilisateurInscritService {
    UtilisateurInscrit getUtilisateurInscritById(long id);
    UtilisateurInscritDTO createUtilisateurInscrit(UtilisateurInscritDTO utilisateurInscritDTO);
    UtilisateurInscritDTO updateUtilisateurInscrit(long id, UtilisateurInscritDTO utilisateurInscritDTO);
    List<UtilisateurInscritDTO> getAllUtilisateurInscrits();
    List<UtilisateurInscritDTO> rechercherUtilisateur(String critere);
}