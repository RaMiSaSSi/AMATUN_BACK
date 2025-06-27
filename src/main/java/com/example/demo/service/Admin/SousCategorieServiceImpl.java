package com.example.demo.service.Admin;

        import com.example.demo.dto.SousCategorieDTO;
        import com.example.demo.model.Categorie;
        import com.example.demo.model.SousCategorie;
        import com.example.demo.repository.CategorieRepository;
        import com.example.demo.repository.SousCategorieRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
        public class SousCategorieServiceImpl implements SousCategorieService {

            @Autowired
            private SousCategorieRepository sousCategorieRepository;

            @Autowired
            private CategorieRepository categorieRepository;

            @Override
            public SousCategorie getSousCategorieById(long id) {
                return sousCategorieRepository.findById(id).orElse(null);
            }

    @Override
    public SousCategorie createSousCategorie(SousCategorieDTO sousCategorieDTO) {
        SousCategorie sousCategorie = convertToEntity(sousCategorieDTO);
        return sousCategorieRepository.save(sousCategorie);
    }

            @Override
            public void deleteSousCategorie(long id) {
                sousCategorieRepository.deleteById(id);
            }

            @Override
            public List<SousCategorie> findByCategorieId(long categorieId) {
                return sousCategorieRepository.findByCategorieId(categorieId);
            }

            @Override
            public List<SousCategorie> getAllSousCategories() {
                return sousCategorieRepository.findAll();
            }
    @Override
    public SousCategorieDTO convertToDTO(SousCategorie sousCategorie) {
        SousCategorieDTO dto = new SousCategorieDTO();
        dto.setId(sousCategorie.getId());
        dto.setNom(sousCategorie.getNom());
        dto.setCategorieId(sousCategorie.getCategorie().getId());
        // Retrieve the boutiqueId through the BoutiqueCategorie relationship
        sousCategorie.getCategorie().getBoutiqueCategories().stream()
                .findFirst()
                .ifPresent(boutiqueCategorie -> dto.setBoutiqueId(boutiqueCategorie.getBoutique().getId()));
        return dto;
    }
    @Override
    public SousCategorieDTO updateSousCategorie(long id, SousCategorieDTO sousCategorieDTO) {
        SousCategorie sousCategorie = sousCategorieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SousCategorie not found with id: " + id));
        sousCategorie.setNom(sousCategorieDTO.getNom());
        sousCategorie.setCategorie(sousCategorie.getCategorie());
        // Update other fields as needed
        SousCategorie updatedSousCategorie = sousCategorieRepository.save(sousCategorie);
        return convertToDTO(updatedSousCategorie);
    }
            private SousCategorie convertToEntity(SousCategorieDTO sousCategorieDTO) {
                SousCategorie sousCategorie = new SousCategorie();
                sousCategorie.setId(sousCategorieDTO.getId());
                sousCategorie.setNom(sousCategorieDTO.getNom());

                Categorie categorie = categorieRepository.findById(sousCategorieDTO.getCategorieId())
                        .orElseThrow(() -> new IllegalArgumentException("Categorie not found"));
                sousCategorie.setCategorie(categorie);

                return sousCategorie;
            }
        }