package com.example.demo.service.Admin;

    import com.example.demo.dto.CategorieDTO;
    import com.example.demo.dto.SousCategorieDTO;
    import com.example.demo.model.Categorie;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;

    import java.util.List;
    import java.util.Set;

    public interface CategorieService {
        List<CategorieDTO> getAllCategories();
        CategorieDTO getCategorieById(long id);
        Categorie createCategorie(CategorieDTO categorieDTO);
        Categorie updateCategorie(long id, CategorieDTO categorieDTO);
        void deleteCategorie(long id);
        List<SousCategorieDTO> getSousCategoriesByCategorieId(long categorieId);
        void addCategorieToBoutiques(long categorieId, Set<Long> boutiqueIds);
        CategorieDTO getCategorieWithBoutiques(long id);
        void addExistingCategorieToExistingBoutique(long categorieId, long boutiqueId);
        List<CategorieDTO> getCategoriesByBoutiqueId(long boutiqueId);
        Page<CategorieDTO> getAllCategoriesPagination(Pageable pageable);
    }