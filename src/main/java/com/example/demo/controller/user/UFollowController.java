package com.example.demo.controller.user;

import com.example.demo.dto.FollowDTO;
import com.example.demo.service.User.UFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/follows")
public class UFollowController {

    @Autowired
    private UFollowService UfollowService;

    @GetMapping
    public ResponseEntity<List<FollowDTO>> getAllFollows() {
        List<FollowDTO> allFollows = UfollowService.getAllFollows();
        return ResponseEntity.ok(allFollows);
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<FollowDTO>> getFollowsByUserId(@RequestParam Long utilisateurId) {
        List<FollowDTO> follows = UfollowService.getFollowsByUserId(utilisateurId);
        return ResponseEntity.ok(follows);
    }
}