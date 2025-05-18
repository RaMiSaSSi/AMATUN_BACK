package com.example.demo.service.Admin;

import com.example.demo.dto.PersonnelBoutiqueDTO;

import java.util.List;

public interface PersonnelBoutiqueService {
    PersonnelBoutiqueDTO createPersonnelBoutiqueByAdmin(PersonnelBoutiqueDTO personnelBoutiqueDTO);
    PersonnelBoutiqueDTO updatePersonnelBoutique(Long id, PersonnelBoutiqueDTO personnelBoutiqueDTO);
    void deletePersonnelBoutique(Long id);
    PersonnelBoutiqueDTO getPersonnelBoutiqueById(Long id);
    List<PersonnelBoutiqueDTO> getAllPersonnelBoutiques();
}