// service/AdresseService.java
package com.example.demo.service.Admin;

import com.example.demo.model.Adresse;

import java.util.List;

public interface AdresseService {
    Adresse getAdresseById(long id);
    Adresse saveAdresse(Adresse adresse);
    void deleteAdresse(long id);
    List<Adresse> getAllAdresses();
    Adresse updateAdresse(long id, Adresse adresse);
}