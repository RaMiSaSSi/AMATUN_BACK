// controller/AdresseController.java
package com.example.demo.controller.admin;

import com.example.demo.model.Adresse;
import com.example.demo.service.Admin.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/adresse")
public class AdresseController {

    @Autowired
    private AdresseService adresseService;

    @GetMapping("/{id}")
    public Adresse getAdresse(@PathVariable long id) {
        return adresseService.getAdresseById(id);
    }

    @PostMapping
    public Adresse saveAdresse(@RequestBody Adresse adresse) {
        return adresseService.saveAdresse(adresse);
    }

    @DeleteMapping("/{id}")
    public void deleteAdresse(@PathVariable long id) {
        adresseService.deleteAdresse(id);
    }

    @PutMapping("/{id}")
    public Adresse updateAdresse(@PathVariable long id, @RequestBody Adresse adresse) {
        return adresseService.updateAdresse(id, adresse);
    }
    @GetMapping
    public List<Adresse> getAllAdresses() {
        return adresseService.getAllAdresses();
    }
}