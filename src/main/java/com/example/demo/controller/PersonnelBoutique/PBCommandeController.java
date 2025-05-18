package com.example.demo.controller.PersonnelBoutique;

import com.example.demo.dto.CommandeDTO;
import com.example.demo.model.Statut;
import com.example.demo.service.PersonnelBoutique.PBCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnel/boutique/commandes")
public class PBCommandeController {

    @Autowired
    private PBCommandeService pbCommandeService;

    @GetMapping("/boutique/{boutiqueId}")
    public ResponseEntity<List<CommandeDTO>> getCommandesByBoutiqueId(@PathVariable Long boutiqueId) {
        List<CommandeDTO> commandes = pbCommandeService.getCommandesByBoutiqueId(boutiqueId);
        return ResponseEntity.ok(commandes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long id) {
        CommandeDTO commande = pbCommandeService.getCommandeById(id);
        return ResponseEntity.ok(commande);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CommandeDTO> updateCommande(@PathVariable Long id, @RequestBody CommandeDTO commandeDTO) {
        CommandeDTO updatedCommande = pbCommandeService.updateCommande(id, commandeDTO);
        return ResponseEntity.ok(updatedCommande);
    }
    @PutMapping("/{id}/statut")
    public ResponseEntity<CommandeDTO> changeStatut(@PathVariable Long id, @RequestBody Statut statut) {
        CommandeDTO updatedCommande = pbCommandeService.changeStatut(id, statut);
        return ResponseEntity.ok(updatedCommande);
    }
}