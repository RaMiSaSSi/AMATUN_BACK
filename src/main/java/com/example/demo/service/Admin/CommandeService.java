// File: src/main/java/com/example/demo/service/Admin/CommandeService.java

package com.example.demo.service.Admin;

import com.example.demo.dto.CommandeDTO;
import com.example.demo.model.Statut;

import java.util.List;

public interface CommandeService {
    CommandeDTO createCommande(CommandeDTO commandeDTO);
    CommandeDTO getCommandeById(Long id);
    List<CommandeDTO> getAllCommandes();
    CommandeDTO updateCommande(Long id, CommandeDTO commandeDTO); // New method
    CommandeDTO changeStatut(Long id, Statut statut); // New method

}