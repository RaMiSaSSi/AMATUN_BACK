// src/main/java/com/example/demo/service/Admin/AdminServiceImpl.java
package com.example.demo.service.Admin;

import com.example.demo.model.Administrateur;
import com.example.demo.model.Role;
import com.example.demo.repository.UtilisateurInscritRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UtilisateurInscritRepository utilisateurInscritRepository;

    @Override
    public Administrateur createAdmin(Administrateur admin) {
        admin.setRole(Role.ADMIN); // Ensure the role is set to ADMIN
        return utilisateurInscritRepository.save(admin);
    }
}