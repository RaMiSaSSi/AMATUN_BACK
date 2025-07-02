package com.example.demo.controller.admin;

import com.example.demo.model.CategoryShop;
import com.example.demo.service.Admin.AdminCategoryShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/admin/category-shops")
public class AdminCategoryShopController {
    @Autowired
    private AdminCategoryShopService categoryShopService;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<CategoryShop> createCategoryShop(
            @RequestParam String name,
            @RequestParam(value = "logo", required = false) MultipartFile logo) throws IOException {

        CategoryShop categoryShop = new CategoryShop();
        categoryShop.setName(name);

        if (logo != null) {
            String logoPath = saveFile(logo, "uploads/category-shops");
            categoryShop.setLogoPath(logoPath);
        }

        return ResponseEntity.ok(categoryShopService.createCategoryShop(categoryShop));
    }

    private String saveFile(MultipartFile file, String directory) throws IOException {
        Path uploadDir = Paths.get(directory);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        String filePath = uploadDir.resolve(file.getOriginalFilename()).toString();
        Files.write(Paths.get(filePath), file.getBytes());
        return filePath;
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<CategoryShop> updateCategoryShop(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam(value = "logo", required = false) MultipartFile logo) throws IOException {

        CategoryShop categoryShop = categoryShopService.getCategoryShopById(id);
        categoryShop.setName(name);

        if (logo != null) {
            String logoPath = saveFile(logo, "uploads/category-shops");
            categoryShop.setLogoPath(logoPath);
        }

        return ResponseEntity.ok(categoryShopService.updateCategoryShop(id, categoryShop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryShop(@PathVariable Long id) {
        categoryShopService.deleteCategoryShop(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryShop> getCategoryShopById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryShopService.getCategoryShopById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryShop>> getAllCategoryShops() {
        return ResponseEntity.ok(categoryShopService.getAllCategoryShops());
    }
    @GetMapping("/paginated")
    public ResponseEntity<Page<CategoryShop>> getCategoryShopsWithPagination(Pageable pageable) {
        return ResponseEntity.ok(categoryShopService.getCategoryShopsWithPagination(pageable));
    }
}
