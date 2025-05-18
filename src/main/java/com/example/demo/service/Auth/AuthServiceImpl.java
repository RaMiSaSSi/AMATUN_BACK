package com.example.demo.service.Auth;

import com.example.demo.dto.AdresseDTO;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.UtilisateurInscritDTO;
import com.example.demo.model.Adresse;
import com.example.demo.model.Role;
import com.example.demo.model.UtilisateurInscrit;
import com.example.demo.model.VerificationCode;
import com.example.demo.repository.UtilisateurInscritRepository;
import com.example.demo.repository.VerificationCodeRepository;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UtilisateurInscritRepository utilisateurInscritRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public AuthenticationResponse login(String email, String motDePasse) {
        Optional<UtilisateurInscrit> utilisateurInscritOptional = utilisateurInscritRepository.findByEmail(email);
        if (utilisateurInscritOptional.isPresent()) {
            UtilisateurInscrit utilisateurInscrit = utilisateurInscritOptional.get();
            if (passwordEncoder.matches(motDePasse, utilisateurInscrit.getMotDePasse())) {
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        utilisateurInscrit.getEmail(),
                        utilisateurInscrit.getMotDePasse(),
                        List.of(new SimpleGrantedAuthority(utilisateurInscrit.getRole().name()))
                );
                String jwt = jwtUtil.generateToken(userDetails);
                return new AuthenticationResponse(jwt, utilisateurInscrit.getRole().name(), utilisateurInscrit.getId());
            }
        }
        throw new IllegalArgumentException("Invalid email or password");
    }
    // File: src/main/java/com/example/demo/service/Auth/AuthServiceImpl.java
    @Override
    public UtilisateurInscrit findByEmail(String email) {
        return utilisateurInscritRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
    @Override
    public void changePassword(long id, String newPassword) {
        UtilisateurInscrit utilisateurInscrit = utilisateurInscritRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        utilisateurInscrit.setMotDePasse(passwordEncoder.encode(newPassword));
        utilisateurInscritRepository.save(utilisateurInscrit);
    }
    @Override
    public void saveVerificationCode(String email, String code) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setEmail(email);
        verificationCode.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        verificationCodeRepository.save(verificationCode);
    }

    @Override
    public boolean verifyUserAndSave(String email, String code, UtilisateurInscritDTO utilisateurInscritDTO) {
        if (utilisateurInscritRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("An account with this email already exists");
        }
        Optional<VerificationCode> verificationCodeOptional = verificationCodeRepository.findByEmailAndCode(email, code);
        if (verificationCodeOptional.isPresent()) {
            VerificationCode verificationCode = verificationCodeOptional.get();
            if (verificationCode.getExpiryDate().isAfter(LocalDateTime.now())) {
                UtilisateurInscrit utilisateurInscrit = new UtilisateurInscrit();
                utilisateurInscrit.setEmail(utilisateurInscritDTO.getEmail());
                utilisateurInscrit.setMotDePasse(passwordEncoder.encode(utilisateurInscritDTO.getMotDePasse()));
                utilisateurInscrit.setNom(utilisateurInscritDTO.getNom());
                utilisateurInscrit.setPrenom(utilisateurInscritDTO.getPrenom());
                utilisateurInscrit.setTelephone(utilisateurInscritDTO.getTelephone());
                utilisateurInscrit.setAdresseLivraison(convertToEntity(utilisateurInscritDTO.getAdresseLivraison()));
                utilisateurInscrit.setRole(Role.CLIENT);
                utilisateurInscritRepository.save(utilisateurInscrit);
                verificationCodeRepository.delete(verificationCode);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean emailExists(String email) {
        return utilisateurInscritRepository.existsByEmail(email);
    }
    @Override
    public UtilisateurInscrit updateUtilisateurInscrit(long id, UtilisateurInscrit utilisateurInscrit) {
        Optional<UtilisateurInscrit> existingUserOptional = utilisateurInscritRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            UtilisateurInscrit existingUser = existingUserOptional.get();
            existingUser.setNom(utilisateurInscrit.getNom());
            existingUser.setPrenom(utilisateurInscrit.getPrenom());
            existingUser.setTelephone(utilisateurInscrit.getTelephone());
            existingUser.setAdresseLivraison(utilisateurInscrit.getAdresseLivraison());
            existingUser.setEmail(utilisateurInscrit.getEmail());
            return utilisateurInscritRepository.save(existingUser);
        }
        return null;
    }
    private UtilisateurInscritDTO convertToDTO(UtilisateurInscrit utilisateurInscrit) {
        UtilisateurInscritDTO dto = new UtilisateurInscritDTO();
        dto.setId(utilisateurInscrit.getId());
        dto.setEmail(utilisateurInscrit.getEmail());
        dto.setMotDePasse(utilisateurInscrit.getMotDePasse());
        dto.setNom(utilisateurInscrit.getNom());
        dto.setPrenom(utilisateurInscrit.getPrenom());
        dto.setTelephone(utilisateurInscrit.getTelephone());
        dto.setDateInscription(utilisateurInscrit.getDateInscription());
        dto.setRole(utilisateurInscrit.getRole());
        dto.setAdresseLivraison(convertToDTO(utilisateurInscrit.getAdresseLivraison()));
        return dto;
    }

    private UtilisateurInscrit convertToEntity(UtilisateurInscritDTO dto) {
        UtilisateurInscrit utilisateurInscrit = new UtilisateurInscrit();
        utilisateurInscrit.setId(dto.getId());
        utilisateurInscrit.setEmail(dto.getEmail());
        utilisateurInscrit.setMotDePasse(dto.getMotDePasse());
        utilisateurInscrit.setNom(dto.getNom());
        utilisateurInscrit.setPrenom(dto.getPrenom());
        utilisateurInscrit.setTelephone(dto.getTelephone());
        utilisateurInscrit.setDateInscription(dto.getDateInscription());
        utilisateurInscrit.setRole(Role.valueOf(dto.getRole().name()));
        utilisateurInscrit.setAdresseLivraison(convertToEntity(dto.getAdresseLivraison()));
        return utilisateurInscrit;
    }


    private AdresseDTO convertToDTO(Adresse adresse) {
        if (adresse == null) {
            return null;
        }
        AdresseDTO dto = new AdresseDTO();
        dto.setId(adresse.getId());
        dto.setRue(adresse.getRue());
        dto.setCodePostal(adresse.getCodePostal());
        dto.setVille(adresse.getVille());
        dto.setPays(adresse.getPays());
        dto.setLatitude(adresse.getLatitude());
        dto.setLongitude(adresse.getLongitude());
        return dto;
    }

    private Adresse convertToEntity(AdresseDTO dto) {
        if (dto == null) {
            return null;
        }
        Adresse adresse = new Adresse();
        adresse.setId(dto.getId());
        adresse.setRue(dto.getRue());
        adresse.setCodePostal(dto.getCodePostal());
        adresse.setVille(dto.getVille());
        adresse.setPays(dto.getPays());
        adresse.setLatitude(dto.getLatitude());
        adresse.setLongitude(dto.getLongitude());
        return adresse;
    }

}
