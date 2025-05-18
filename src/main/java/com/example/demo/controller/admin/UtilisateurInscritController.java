package com.example.demo.controller.admin;

        import com.example.demo.dto.AuthenticationRequest;
        import com.example.demo.dto.AuthenticationResponse;
        import com.example.demo.dto.UtilisateurInscritDTO;
        import com.example.demo.service.Admin.UtilisateurInscritService;
        import com.example.demo.utils.JwtUtil;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.AuthenticationException;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

        @RestController
        @RequestMapping("/utilisateurinscrit")
        public class UtilisateurInscritController {

            @Autowired
            private UtilisateurInscritService utilisateurInscritService;
            @Autowired
            private AuthenticationManager authenticationManager;
            @Autowired
            private JwtUtil jwtUtil;

            @GetMapping("/{id}")
            public ResponseEntity<UtilisateurInscritDTO> getUtilisateurInscrit(@PathVariable long id) {
                UtilisateurInscritDTO utilisateurInscritDTO = utilisateurInscritService.getUtilisateurInscritById(id).getDTO();
                return ResponseEntity.ok(utilisateurInscritDTO);
            }

            @GetMapping
            public ResponseEntity<List<UtilisateurInscritDTO>> getAllUtilisateurInscrits() {
                List<UtilisateurInscritDTO> utilisateurInscrits = utilisateurInscritService.getAllUtilisateurInscrits();
                return ResponseEntity.ok(utilisateurInscrits);
            }

            @PutMapping("/{id}")
            public ResponseEntity<UtilisateurInscritDTO> updateUtilisateurInscrit(@PathVariable long id, @RequestBody UtilisateurInscritDTO utilisateurInscritDTO) {
                UtilisateurInscritDTO updatedUtilisateurInscrit = utilisateurInscritService.updateUtilisateurInscrit(id, utilisateurInscritDTO);
                return ResponseEntity.ok(updatedUtilisateurInscrit);
            }

            @GetMapping("/search")
            public ResponseEntity<List<UtilisateurInscritDTO>> rechercherUtilisateur(@RequestParam String critere) {
                List<UtilisateurInscritDTO> utilisateurInscrits = utilisateurInscritService.rechercherUtilisateur(critere);
                return ResponseEntity.ok(utilisateurInscrits);
            }

        }