package com.example.demo.controller.admin;

import com.example.demo.dto.CommandeDTO;
import com.example.demo.model.Statut;
import com.example.demo.service.Admin.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @PostMapping
    public ResponseEntity<CommandeDTO> createCommande(@RequestBody CommandeDTO commandeDTO) {
        CommandeDTO createdCommande = commandeService.createCommande(commandeDTO);
        return ResponseEntity.ok(createdCommande);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long id) {
        CommandeDTO commande = commandeService.getCommandeById(id);
        return ResponseEntity.ok(commande);
    }

    @GetMapping
    public ResponseEntity<List<CommandeDTO>> getAllCommandes() {
        List<CommandeDTO> commandes = commandeService.getAllCommandes();
        return ResponseEntity.ok(commandes);
    }
    @PutMapping("/{id}")
    public CommandeDTO updateCommande(@PathVariable Long id, @RequestBody CommandeDTO commandeDTO) {
        return commandeService.updateCommande(id, commandeDTO);
    }
    @PutMapping("/{id}/statut")
    public ResponseEntity<CommandeDTO> changeStatut(@PathVariable Long id, @RequestBody Statut statut) {
        CommandeDTO updatedCommande = commandeService.changeStatut(id, statut);
        return ResponseEntity.ok(updatedCommande);
    }
    @GetMapping("/pagination")
    public ResponseEntity<Page<CommandeDTO>> getAllCommandesPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CommandeDTO> commandes = commandeService.getAllCommandesPaggination(pageable);
        return ResponseEntity.ok(commandes);
    }
}