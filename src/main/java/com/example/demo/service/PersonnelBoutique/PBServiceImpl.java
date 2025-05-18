package com.example.demo.service.PersonnelBoutique;

                        import com.example.demo.dto.AdresseDTO;
                        import com.example.demo.dto.BoutiqueDTO;
                        import com.example.demo.model.Adresse;
                        import com.example.demo.model.Boutique;
                        import com.example.demo.model.PersonnelBoutique;
                        import com.example.demo.repository.PersonnelBoutiqueRepository;
                        import org.springframework.beans.factory.annotation.Autowired;
                        import org.springframework.stereotype.Service;

                        @Service
                        public class PBServiceImpl implements PBService {
                            @Autowired
                            private PersonnelBoutiqueRepository personnelBoutiqueRepository;

                            @Override
                            public BoutiqueDTO getBoutiqueForAuthenticatedPersonnel(Long personnelId) {
                                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                        .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                return personnelBoutique.getBoutique().getDTO();
                            }

                            @Override
                            public BoutiqueDTO updateBoutiqueForPersonnel(Long personnelId, BoutiqueDTO boutiqueDTO) {
                                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                        .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                Boutique boutique = personnelBoutique.getBoutique();
                                boutique.setNom(boutiqueDTO.getNom());
                                boutique.setTelephone(boutiqueDTO.getTelephone());
                                boutique.setImage(boutiqueDTO.getImage());
                                boutique.setBanner(boutiqueDTO.getBanner());
                                boutique.setEmail(boutiqueDTO.getEmail());
                                // Update other fields as needed
                                personnelBoutiqueRepository.save(personnelBoutique);
                                return boutique.getDTO();
                            }

                            @Override
                            public AdresseDTO updateBoutiqueAdresseForPersonnel(Long personnelId, AdresseDTO adresseDTO) {
                                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                        .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                Boutique boutique = personnelBoutique.getBoutique();
                                Adresse adresse = boutique.getAdresse();
                                adresse.setRue(adresseDTO.getRue());
                                adresse.setVille(adresseDTO.getVille());
                                adresse.setCodePostal(adresseDTO.getCodePostal());
                                // Update other fields as needed
                                personnelBoutiqueRepository.save(personnelBoutique);
                                return adresse.toDTO();
                            }

                            @Override
                            public AdresseDTO getAdresse(Long personnelId) {
                                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                        .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                return personnelBoutique.getBoutique().getAdresse().toDTO();
                            }

                            @Override
                            public int getBoutiqueViews(Long personnelId) {
                                PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(personnelId)
                                        .orElseThrow(() -> new IllegalArgumentException("Personnel not found with id: " + personnelId));
                                return personnelBoutique.getBoutique().getViews();
                            }
                        }