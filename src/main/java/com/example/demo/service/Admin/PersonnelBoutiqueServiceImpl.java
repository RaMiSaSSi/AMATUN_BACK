package com.example.demo.service.Admin;

    import com.example.demo.dto.PersonnelBoutiqueDTO;
    import com.example.demo.model.Boutique;
    import com.example.demo.model.PersonnelBoutique;
    import com.example.demo.model.Role;
    import com.example.demo.repository.BoutiqueRepository;
    import com.example.demo.repository.PersonnelBoutiqueRepository;
    import com.example.demo.service.Auth.EmailService;
    import jakarta.mail.MessagingException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.Date;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class PersonnelBoutiqueServiceImpl implements PersonnelBoutiqueService {

        @Autowired
        private PersonnelBoutiqueRepository personnelBoutiqueRepository;

        @Autowired
        private BoutiqueRepository boutiqueRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private EmailService emailService;

        @Override
        public PersonnelBoutiqueDTO createPersonnelBoutiqueByAdmin(PersonnelBoutiqueDTO personnelBoutiqueDTO) {
            Boutique boutique = boutiqueRepository.findById(personnelBoutiqueDTO.getBoutiqueId())
                    .orElseThrow(() -> new IllegalArgumentException("Boutique not found"));

            PersonnelBoutique personnelBoutique = new PersonnelBoutique();
            personnelBoutique.setEmail(personnelBoutiqueDTO.getEmail());
            personnelBoutique.setMotDePasse(passwordEncoder.encode(personnelBoutiqueDTO.getMotDePasse()));
            personnelBoutique.setRole(Role.PERSONNEL_BOUTIQUE);
            personnelBoutique.setFirstLogin(true);
            personnelBoutique.setBoutique(boutique);
            personnelBoutique.setUsername(personnelBoutiqueDTO.getUsername());
            personnelBoutique.setNom(personnelBoutiqueDTO.getNom());
            personnelBoutique.setPrenom(personnelBoutiqueDTO.getPrenom());
            personnelBoutique.setTelephone(personnelBoutiqueDTO.getTelephone());
            personnelBoutiqueRepository.save(personnelBoutique);

            try {
                emailService.sendPersonnelBoutiqueEmail(
                        personnelBoutiqueDTO.getEmail(),
                        personnelBoutiqueDTO.getUsername(),
                        personnelBoutiqueDTO.getMotDePasse()
                );
            } catch (MessagingException e) {
                throw new RuntimeException("Failed to send email", e);
            }

            return personnelBoutiqueDTO;
        }

        @Override
        public PersonnelBoutiqueDTO updatePersonnelBoutique(Long id, PersonnelBoutiqueDTO personnelBoutiqueDTO) {
            PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("PersonnelBoutique not found"));

            personnelBoutique.setNom(personnelBoutiqueDTO.getNom());
            personnelBoutique.setPrenom(personnelBoutiqueDTO.getPrenom());
            personnelBoutique.setEmail(personnelBoutiqueDTO.getEmail());
            personnelBoutique.setTelephone(personnelBoutiqueDTO.getTelephone());
            personnelBoutique.setUsername(personnelBoutiqueDTO.getUsername());
            personnelBoutiqueRepository.save(personnelBoutique);

            return personnelBoutiqueDTO;
        }

        @Override
        public void deletePersonnelBoutique(Long id) {
            personnelBoutiqueRepository.deleteById(id);
        }

        @Override
        public PersonnelBoutiqueDTO getPersonnelBoutiqueById(Long id) {
            PersonnelBoutique personnelBoutique = personnelBoutiqueRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("PersonnelBoutique not found"));
            return personnelBoutique.toDTO();
        }

        @Override
        public List<PersonnelBoutiqueDTO> getAllPersonnelBoutiques() {
            return personnelBoutiqueRepository.findAll().stream()
                    .map(PersonnelBoutique::toDTO)
                    .collect(Collectors.toList());
        }
    }