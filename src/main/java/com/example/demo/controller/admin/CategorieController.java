package com.example.demo.controller.admin;

    import com.example.demo.dto.CategorieDTO;
    import com.example.demo.dto.SousCategorieDTO;
    import com.example.demo.model.Categorie;
    import com.example.demo.service.Admin.CategorieService;
    import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/admin/categorie")
    public class CategorieController {

        @Autowired
        private CategorieService categorieService;

        @GetMapping
        public ResponseEntity<List<CategorieDTO>> getAllCategories() {
            List<CategorieDTO> categories = categorieService.getAllCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<CategorieDTO> getCategorieById(@PathVariable long id) {
            CategorieDTO categorieDTO = categorieService.getCategorieById(id);
            return categorieDTO != null ? new ResponseEntity<>(categorieDTO, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Categorie> createCategorie(
            @RequestParam String nom,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setNom(nom);

        if (image != null) {
            String imagePath = saveFile(image, "uploads/categories");
            categorieDTO.setImagePath(imagePath);
        }

        Categorie createdCategorie = categorieService.createCategorie(categorieDTO);
        return new ResponseEntity<>(createdCategorie, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Categorie> updateCategorie(
            @PathVariable long id,
            @RequestParam String nom,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setNom(nom);

        if (image != null) {
            String imagePath = saveFile(image, "uploads/categories");
            categorieDTO.setImagePath(imagePath);
        }

        Categorie updatedCategorie = categorieService.updateCategorie(id, categorieDTO);
        return new ResponseEntity<>(updatedCategorie, HttpStatus.OK);
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

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCategorie(@PathVariable long id) {
            categorieService.deleteCategorie(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/{categorieId}/sousCategories")
        public ResponseEntity<List<SousCategorieDTO>> getSousCategoriesByCategorieId(@PathVariable long categorieId) {
            List<SousCategorieDTO> sousCategories = categorieService.getSousCategoriesByCategorieId(categorieId);
            return new ResponseEntity<>(sousCategories, HttpStatus.OK);
        }
    }