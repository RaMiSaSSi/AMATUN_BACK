package com.example.demo.controller.admin;

        import com.example.demo.dto.SousCategorieDTO;
        import com.example.demo.model.SousCategorie;
        import com.example.demo.service.Admin.SousCategorieService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.stream.Collectors;

        @RestController
        @RequestMapping("/admin/souscategorie")
        public class SousCategorieController {

            @Autowired
            private SousCategorieService sousCategorieService;

            @GetMapping("/{id}")
            public ResponseEntity<SousCategorieDTO> getSousCategorie(@PathVariable long id) {
                SousCategorie sousCategorie = sousCategorieService.getSousCategorieById(id);
                if (sousCategorie != null) {
                    SousCategorieDTO responseDTO = sousCategorieService.convertToDTO(sousCategorie);
                    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            @PutMapping("/{id}")
            public ResponseEntity<SousCategorieDTO> updateSousCategorie(@PathVariable long id, @RequestBody SousCategorieDTO sousCategorieDTO) {
                SousCategorieDTO updatedSousCategorieDTO = sousCategorieService.updateSousCategorie(id, sousCategorieDTO);
                return new ResponseEntity<>(updatedSousCategorieDTO, HttpStatus.OK);
            }

            @PostMapping
            public ResponseEntity<SousCategorieDTO> createSousCategorie(@RequestBody SousCategorieDTO sousCategorieDTO) {
                SousCategorieDTO savedSousCategorieDTO = sousCategorieService.convertToDTO(sousCategorieService.createSousCategorie(sousCategorieDTO));
                return new ResponseEntity<>(savedSousCategorieDTO, HttpStatus.CREATED);
            }

            @DeleteMapping("/{id}")
            public ResponseEntity<Void> deleteSousCategorie(@PathVariable long id) {
                sousCategorieService.deleteSousCategorie(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            @GetMapping("/byCategorie/{categorieId}")
            public ResponseEntity<List<SousCategorieDTO>> getSousCategoriesByCategorieId(@PathVariable long categorieId) {
                List<SousCategorie> sousCategories = sousCategorieService.findByCategorieId(categorieId);
                List<SousCategorieDTO> responseDTOs = sousCategories.stream()
                        .map(sousCategorieService::convertToDTO)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
            }

            @GetMapping
            public ResponseEntity<List<SousCategorieDTO>> getAllSousCategories() {
                List<SousCategorie> sousCategories = sousCategorieService.getAllSousCategories();
                List<SousCategorieDTO> responseDTOs = sousCategories.stream()
                        .map(sousCategorieService::convertToDTO)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
            }
        }