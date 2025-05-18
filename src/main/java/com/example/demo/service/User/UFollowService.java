package com.example.demo.service.User;

import com.example.demo.dto.FollowDTO;

import java.util.List;

public interface UFollowService {
    List<FollowDTO> getAllFollows(); // New method
    List<FollowDTO> getFollowsByUserId(Long utilisateurId); // New method

}
