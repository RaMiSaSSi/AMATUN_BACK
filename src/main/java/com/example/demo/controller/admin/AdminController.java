// src/main/java/com/example/demo/controller/AdminController.java
package com.example.demo.controller.admin;

import com.example.demo.model.Administrateur;
import com.example.demo.service.Admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public Administrateur createAdmin(@RequestBody Administrateur admin) {
        return adminService.createAdmin(admin);
    }
}