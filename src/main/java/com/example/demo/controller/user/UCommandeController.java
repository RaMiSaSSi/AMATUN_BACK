package com.example.demo.controller.user;

import com.example.demo.dto.CommandeDTO;
import com.example.demo.service.User.UCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/commandes")
public class UCommandeController {

    @Autowired
    private UCommandeService uCommandeService;

    @PostMapping
    public ResponseEntity<CommandeDTO> createCommande(@RequestBody CommandeDTO commandeDTO) {
        CommandeDTO createdCommande = uCommandeService.createCommande(commandeDTO);
        return ResponseEntity.ok(createdCommande);
    }
    @GetMapping("/products/confirmed")
    public ResponseEntity<List<Long>> getProductIdsByConfirmedOrders(@RequestParam Long userId) {
        List<Long> productIds = uCommandeService.getProductIdsByUserAndConfirmedStatus(userId);
        return ResponseEntity.ok(productIds);
    }
}