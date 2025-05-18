package com.example.demo.service.PersonnelBoutique;

import com.example.demo.dto.AdresseDTO;
import com.example.demo.dto.BoutiqueDTO;

public interface PBService {
    BoutiqueDTO getBoutiqueForAuthenticatedPersonnel(Long personnelId);
    BoutiqueDTO updateBoutiqueForPersonnel(Long personnelId, BoutiqueDTO boutiqueDTO);
    AdresseDTO updateBoutiqueAdresseForPersonnel(Long personnelId, AdresseDTO adresseDTO);
    AdresseDTO getAdresse(Long personnelId);
    int getBoutiqueViews(Long personnelId); // New method

}