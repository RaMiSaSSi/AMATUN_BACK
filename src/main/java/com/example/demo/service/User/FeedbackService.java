package com.example.demo.service.User;

import com.example.demo.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackDTO addFeedback(Long boutiqueId, Long utilisateurId, int rating, String comment);
    List<FeedbackDTO> getFeedbackByBoutiqueId(Long boutiqueId);
    List<FeedbackDTO> getAllFeedbacksGroupedByBoutique();
    boolean hasUserGivenFeedback(Long boutiqueId, Long utilisateurId);
    FeedbackDTO updateFeedback(Long feedbackId, int rating, String comment);
}