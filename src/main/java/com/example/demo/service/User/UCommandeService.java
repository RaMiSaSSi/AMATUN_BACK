package com.example.demo.service.User;

import com.example.demo.dto.CommandeDTO;

import java.util.List;

public interface UCommandeService {
    CommandeDTO createCommande(CommandeDTO commandeDTO);
    List<Long> getProductIdsByUserAndConfirmedStatus(Long userId);
}
