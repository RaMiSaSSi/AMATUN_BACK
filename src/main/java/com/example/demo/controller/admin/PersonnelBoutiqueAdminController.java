package com.example.demo.controller.admin;

    import com.example.demo.dto.PersonnelBoutiqueDTO;
    import com.example.demo.service.Admin.PersonnelBoutiqueService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/admin/personnelboutique")
    public class PersonnelBoutiqueAdminController {

        @Autowired
        private PersonnelBoutiqueService personnelBoutiqueService;

        @PostMapping("/create")
        public ResponseEntity<PersonnelBoutiqueDTO> createPersonnelBoutique(@RequestBody PersonnelBoutiqueDTO personnelBoutiqueDTO) {
            PersonnelBoutiqueDTO createdPersonnelBoutique = personnelBoutiqueService.createPersonnelBoutiqueByAdmin(personnelBoutiqueDTO);
            return ResponseEntity.ok(createdPersonnelBoutique);
        }

        @PutMapping("/{id}")
        public ResponseEntity<PersonnelBoutiqueDTO> updatePersonnelBoutique(@PathVariable Long id, @RequestBody PersonnelBoutiqueDTO personnelBoutiqueDTO) {
            PersonnelBoutiqueDTO updatedPersonnelBoutique = personnelBoutiqueService.updatePersonnelBoutique(id, personnelBoutiqueDTO);
            return ResponseEntity.ok(updatedPersonnelBoutique);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePersonnelBoutique(@PathVariable Long id) {
            personnelBoutiqueService.deletePersonnelBoutique(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/{id}")
        public ResponseEntity<PersonnelBoutiqueDTO> getPersonnelBoutiqueById(@PathVariable Long id) {
            PersonnelBoutiqueDTO personnelBoutique = personnelBoutiqueService.getPersonnelBoutiqueById(id);
            return ResponseEntity.ok(personnelBoutique);
        }

        @GetMapping
        public ResponseEntity<List<PersonnelBoutiqueDTO>> getAllPersonnelBoutiques() {
            List<PersonnelBoutiqueDTO> personnelBoutiques = personnelBoutiqueService.getAllPersonnelBoutiques();
            return ResponseEntity.ok(personnelBoutiques);
        }
    }