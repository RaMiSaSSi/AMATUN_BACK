package com.example.demo.service.Admin;

                        import com.example.demo.dto.UtilisateurInscritDTO;
                        import com.example.demo.model.UtilisateurInscrit;
                        import com.example.demo.repository.UtilisateurInscritRepository;
                        import org.springframework.beans.factory.annotation.Autowired;
                        import org.springframework.stereotype.Service;

                        import java.util.List;
                        import java.util.stream.Collectors;

                        @Service
                        public class UtilisateurInscritServiceImpl implements UtilisateurInscritService {

                            @Autowired
                            private UtilisateurInscritRepository utilisateurInscritRepository;

                            @Override
                            public UtilisateurInscrit getUtilisateurInscritById(long id) {
                                return utilisateurInscritRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("Utilisateur not found with id: " + id));
                            }

                            @Override
                            public UtilisateurInscritDTO createUtilisateurInscrit(UtilisateurInscritDTO utilisateurInscritDTO) {
                                UtilisateurInscrit utilisateurInscrit = new UtilisateurInscrit();
                                utilisateurInscrit.setEmail(utilisateurInscritDTO.getEmail());
                                utilisateurInscrit.setMotDePasse(utilisateurInscritDTO.getMotDePasse());
                                utilisateurInscrit.setNom(utilisateurInscritDTO.getNom());
                                utilisateurInscrit.setPrenom(utilisateurInscritDTO.getPrenom());
                                utilisateurInscrit.setTelephone(utilisateurInscritDTO.getTelephone());
                                utilisateurInscrit.setRole(utilisateurInscritDTO.getRole());
                                utilisateurInscrit.setAdresseLivraison(utilisateurInscritDTO.getAdresseLivraison().toEntity());

                                utilisateurInscrit = utilisateurInscritRepository.save(utilisateurInscrit);
                                return utilisateurInscrit.getDTO();
                            }

                            @Override
                            public UtilisateurInscritDTO updateUtilisateurInscrit(long id, UtilisateurInscritDTO utilisateurInscritDTO) {
                                UtilisateurInscrit utilisateurInscrit = utilisateurInscritRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("Utilisateur not found with id: " + id));

                                utilisateurInscrit.setEmail(utilisateurInscritDTO.getEmail());
                                utilisateurInscrit.setMotDePasse(utilisateurInscritDTO.getMotDePasse());
                                utilisateurInscrit.setNom(utilisateurInscritDTO.getNom());
                                utilisateurInscrit.setPrenom(utilisateurInscritDTO.getPrenom());
                                utilisateurInscrit.setTelephone(utilisateurInscritDTO.getTelephone());
                                utilisateurInscrit.setRole(utilisateurInscritDTO.getRole());
                                utilisateurInscrit.setAdresseLivraison(utilisateurInscritDTO.getAdresseLivraison().toEntity());

                                utilisateurInscrit = utilisateurInscritRepository.save(utilisateurInscrit);
                                return utilisateurInscrit.getDTO();
                            }

                            @Override
                            public List<UtilisateurInscritDTO> getAllUtilisateurInscrits() {
                                return utilisateurInscritRepository.findAll().stream()
                                        .map(UtilisateurInscrit::getDTO)
                                        .collect(Collectors.toList());
                            }

                            @Override
                            public List<UtilisateurInscritDTO> rechercherUtilisateur(String critere) {
                                return utilisateurInscritRepository.findByCritere(critere).stream()
                                        .map(UtilisateurInscrit::getDTO)
                                        .collect(Collectors.toList());
                            }
                        }