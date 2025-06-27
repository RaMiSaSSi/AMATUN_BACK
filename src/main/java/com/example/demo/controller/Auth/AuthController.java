package com.example.demo.controller.Auth;

                    import com.example.demo.dto.*;
                    import com.example.demo.model.Adresse;
                    import com.example.demo.model.PersonnelBoutique;
                    import com.example.demo.model.UtilisateurInscrit;
                    import com.example.demo.service.Auth.AuthService;
                    import com.example.demo.service.Auth.EmailService;
                    import com.example.demo.utils.JwtUtil;
                    import org.springframework.beans.factory.annotation.Autowired;
                    import org.springframework.http.HttpStatus;
                    import org.springframework.http.ResponseEntity;
                    import org.springframework.security.authentication.AuthenticationManager;
                    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
                    import org.springframework.security.core.Authentication;
                    import org.springframework.security.core.AuthenticationException;
                    import org.springframework.security.core.userdetails.UserDetails;
                    import org.springframework.security.core.userdetails.UserDetailsService;
                    import org.springframework.web.bind.annotation.*;

                    import java.util.Random;

                    @RestController
                    @RequestMapping("/auth")
                    public class AuthController {

                        @Autowired
                        private AuthService authService;

                        @Autowired
                        private AuthenticationManager authenticationManager;
                        @Autowired
                        private UserDetailsService userDetailsService;
                        @Autowired
                        private JwtUtil jwtUtil;
                        @Autowired
                        private EmailService emailService;

                        @PostMapping("/signup")
                        public ResponseEntity<String> signup(@RequestBody UtilisateurInscritDTO utilisateurInscritDTO) {
                            if (authService.emailExists(utilisateurInscritDTO.getEmail())) {
                                return ResponseEntity.badRequest().body("An account with this email already exists");
                            }
                            String verificationCode = String.format("%04d", new Random().nextInt(10000));
                            authService.saveVerificationCode(utilisateurInscritDTO.getEmail(), verificationCode);
                            emailService.sendVerificationEmail(utilisateurInscritDTO.getEmail(), "Email Verification", "Your verification code is: " + verificationCode);
                            return ResponseEntity.ok("Verification code sent to your email");
                        }

                        @PostMapping("/login")
                        public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
                            try {
                                Authentication authentication = authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
                                );
                                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                                String jwt = jwtUtil.generateToken(userDetails);
                                UtilisateurInscrit utilisateurInscrit = authService.findByEmail(authenticationRequest.getEmail());
                                UtilisateurInscritDTO utilisateurInscritDTO = convertToDTO(utilisateurInscrit);
                                AuthenticationResponse response = new AuthenticationResponse(jwt, utilisateurInscritDTO.getRole().name(), utilisateurInscritDTO.getId());
                                return ResponseEntity.ok(response);
                            } catch (AuthenticationException e) {
                                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
                            }
                        }

                        @PostMapping("/verify")
                        public ResponseEntity<String> verifyUser(@RequestBody UtilisateurInscritDTO utilisateurInscritDTO, @RequestParam("code") String code) {
                            boolean isVerified = authService.verifyUserAndSave(utilisateurInscritDTO.getEmail(), code, utilisateurInscritDTO);
                            if (isVerified) {
                                return ResponseEntity.ok("User verified and saved successfully");
                            } else {
                                return ResponseEntity.badRequest().body("Invalid or expired code");
                            }
                        }

                        @PutMapping("/change-password/{id}")
                        public ResponseEntity<String> changePassword(@PathVariable long id, @RequestBody String newPassword) {
                            try {
                                authService.changePassword(id, newPassword);
                                return ResponseEntity.ok("Password changed successfully");
                            } catch (Exception e) {
                                return ResponseEntity.badRequest().body("Error changing password");
                            }
                        }
                        @GetMapping("/userinfo")
                        public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authHeader) {
                            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                                String jwtToken = authHeader.substring(7);
                                try {
                                    UtilisateurInscritDTO utilisateurInscritDTO = authService.getUserInfo(jwtToken);
                                    return ResponseEntity.ok(utilisateurInscritDTO);
                                } catch (Exception e) {
                                    return ResponseEntity.badRequest().body("Invalid token");
                                }
                            }
                            return ResponseEntity.badRequest().body("Authorization header missing or invalid");
                        }

                        private PersonnelBoutiqueDTO convertToPersonnelBoutiqueDTO(PersonnelBoutique personnelBoutique) {
                            PersonnelBoutiqueDTO dto = new PersonnelBoutiqueDTO();
                            dto.setId(personnelBoutique.getId());
                            dto.setEmail(personnelBoutique.getEmail());
                            dto.setMotDePasse(personnelBoutique.getMotDePasse());
                            dto.setNom(personnelBoutique.getNom());
                            dto.setPrenom(personnelBoutique.getPrenom());
                            dto.setTelephone(personnelBoutique.getTelephone());
                            dto.setRole(personnelBoutique.getRole());
                            dto.setAdresseLivraison(convertToDTO(personnelBoutique.getAdresseLivraison()));
                            dto.setBoutiqueId(personnelBoutique.getBoutique().getId());
                            return dto;
                        }
                        @PutMapping("/update/{id}")
                        public ResponseEntity<?> updateUserInfo(@PathVariable long id, @RequestBody UtilisateurInscrit utilisateurInscrit) {
                            if (utilisateurInscrit == null) {
                                return ResponseEntity.badRequest().body("User information cannot be null");
                            }
                            try {
                                UtilisateurInscrit updatedUser = authService.updateUtilisateurInscrit(id, utilisateurInscrit);
                                if (updatedUser == null) {
                                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
                                }
                                UtilisateurInscritDTO dto = convertToDTO(updatedUser);
                                return ResponseEntity.ok(dto);
                            } catch (Exception e) {
                                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user information: " + e.getMessage());
                            }
                        }

                        private UtilisateurInscritDTO convertToDTO(UtilisateurInscrit utilisateurInscrit) {
                            UtilisateurInscritDTO dto = new UtilisateurInscritDTO();
                            dto.setId(utilisateurInscrit.getId());
                            dto.setEmail(utilisateurInscrit.getEmail());
                            dto.setMotDePasse(utilisateurInscrit.getMotDePasse());
                            dto.setNom(utilisateurInscrit.getNom());
                            dto.setPrenom(utilisateurInscrit.getPrenom());
                            dto.setTelephone(utilisateurInscrit.getTelephone());
                            dto.setRole(utilisateurInscrit.getRole());
                            dto.setAdresseLivraison(convertToDTO(utilisateurInscrit.getAdresseLivraison()));
                            return dto;
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
                    }