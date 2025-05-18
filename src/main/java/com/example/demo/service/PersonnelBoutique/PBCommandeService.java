// File: src/main/java/com/example/demo/service/PersonnelBoutique/PBCommandeService.java

package com.example.demo.service.PersonnelBoutique;

import com.example.demo.dto.CommandeDTO;
import com.example.demo.model.Statut;

import java.util.List;

public interface PBCommandeService {
    List<CommandeDTO> getCommandesByBoutiqueId(Long boutiqueId);
    CommandeDTO getCommandeById(Long id); // New method
    CommandeDTO updateCommande(Long id, CommandeDTO commandeDTO);
    CommandeDTO changeStatut(Long id, Statut statut); // New method
}