package com.example.demo.service.Auth;

import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.UtilisateurInscritDTO;
import com.example.demo.model.UtilisateurInscrit;

public interface AuthService {
    void changePassword(long id, String newPassword);
    AuthenticationResponse login(String email, String motDePasse);
    void saveVerificationCode(String email, String code);
    boolean verifyUserAndSave(String email, String code, UtilisateurInscritDTO utilisateurInscritDTO);
    UtilisateurInscrit findByEmail(String email);
    UtilisateurInscrit updateUtilisateurInscrit(long id, UtilisateurInscrit utilisateurInscrit);
    boolean emailExists(String email);
    UtilisateurInscritDTO getUserInfo(String jwtToken);

}
