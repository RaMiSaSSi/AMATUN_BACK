// BoutiqueController.java
package com.example.demo.controller.admin;

import com.example.demo.dto.BoutiqueDTO;
import com.example.demo.dto.CategorieDTO;
import com.example.demo.service.Admin.BoutiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/admin/boutiques")
@RequiredArgsConstructor
public class BoutiqueController {

    @Autowired
    private BoutiqueService boutiqueService;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<BoutiqueDTO> createBoutique(
            @RequestParam("nom") String nom,
            @RequestParam("telephone") String telephone,
            @RequestParam("email") String email,
            @RequestParam("adresseId") Long adresseId,
            @RequestParam("categoryshopId") Long categoryShopId,
            @RequestParam(value = "categorieIds", required = false) List<Long> categorieIds,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "banner", required = false) MultipartFile banner) {
        try {
            BoutiqueDTO boutiqueDTO = new BoutiqueDTO();
            boutiqueDTO.setNom(nom);
            boutiqueDTO.setTelephone(telephone);
            boutiqueDTO.setEmail(email);
            boutiqueDTO.setAdresseId(adresseId);
            boutiqueDTO.setCategoryShopId(categoryShopId);
            boutiqueDTO.setCategorieIds(categorieIds);

            if (image != null && !image.isEmpty()) {
                String imagePath = saveFile(image, "uploads/images");
                boutiqueDTO.setImagePath(imagePath);
            }

            if (banner != null && !banner.isEmpty()) {
                String bannerPath = saveFile(banner, "uploads/banners");
                boutiqueDTO.setBannerPath(bannerPath);
            }

            BoutiqueDTO createdBoutique = boutiqueService.createBoutique(boutiqueDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBoutique);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String saveFile(MultipartFile file, String directory) throws IOException {
        Path uploadDir = Paths.get(directory);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        String filePath = uploadDir.resolve(file.getOriginalFilename()).toString();
        System.out.println("File path: " + filePath);
        Files.write(Paths.get(filePath), file.getBytes());
        return filePath;
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<BoutiqueDTO> updateBoutique(
            @PathVariable long id,
            @RequestParam("nom") String nom,
            @RequestParam("telephone") String telephone,
            @RequestParam("email") String email,
            @RequestParam("adresseId") Long adresseId,
            @RequestParam("categoryshopId") Long categoryShopId,
            @RequestParam(value = "categorieIds", required = false) List<Long> categorieIds,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "banner", required = false) MultipartFile banner) {
        try {
            BoutiqueDTO boutiqueDTO = new BoutiqueDTO();
            boutiqueDTO.setNom(nom);
            boutiqueDTO.setTelephone(telephone);
            boutiqueDTO.setEmail(email);
            boutiqueDTO.setAdresseId(adresseId);
            boutiqueDTO.setCategoryShopId(categoryShopId);
            boutiqueDTO.setCategorieIds(categorieIds);

            if (image != null && !image.isEmpty()) {
                String imagePath = saveFile(image, "uploads/images");
                boutiqueDTO.setImagePath(imagePath);
            }

            if (banner != null && !banner.isEmpty()) {
                String bannerPath = saveFile(banner, "uploads/banners");
                boutiqueDTO.setBannerPath(bannerPath);
            }

            BoutiqueDTO updatedBoutique = boutiqueService.updateBoutique(id, boutiqueDTO);
            return ResponseEntity.ok(updatedBoutique);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoutique(@PathVariable long id) {
        boutiqueService.deleteBoutique(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoutiqueDTO> getBoutiqueById(@PathVariable long id) {
        BoutiqueDTO boutique = boutiqueService.getBoutiqueById(id);
        return ResponseEntity.ok(boutique);
    }

    @GetMapping
    public ResponseEntity<Page<BoutiqueDTO>> getAllBoutiques(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BoutiqueDTO> boutiques = boutiqueService.getAllBoutiques(pageable);
        return ResponseEntity.ok(boutiques);
    }
    @GetMapping("/{id}/categories")
    public ResponseEntity<List<CategorieDTO>> getCategoriesByBoutiqueId(@PathVariable long id) {
        List<CategorieDTO> categories = boutiqueService.getCategoriesByBoutiqueId(id);
        return ResponseEntity.ok(categories);
    }
    @DeleteMapping("/{boutiqueId}/categories/{categorieId}")
    public ResponseEntity<Void> removeCategorieFromBoutique(@PathVariable long boutiqueId, @PathVariable long categorieId) {
        boutiqueService.removeCategorieFromBoutique(boutiqueId, categorieId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{boutiqueId}/categories/{categorieId}")
    public ResponseEntity<Void> addCategorieToBoutique(@PathVariable long boutiqueId, @PathVariable long categorieId) {
        boutiqueService.addCategorieToBoutique(boutiqueId, categorieId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{id}/unassociated-categories")
    public ResponseEntity<List<CategorieDTO>> getUnassociatedCategories(@PathVariable long id) {
        List<CategorieDTO> categories = boutiqueService.getUnassociatedCategories(id);
        return ResponseEntity.ok(categories);
    }
}