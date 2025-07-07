package com.example.demo.controller.user;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.service.User.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDTO> addFeedback(
            @RequestParam Long boutiqueId,
            @RequestParam Long utilisateurId,
            @RequestParam int rating,
            @RequestParam(required = false) String comment) {
        FeedbackDTO feedback = feedbackService.addFeedback(boutiqueId, utilisateurId, rating, comment);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/boutique/{boutiqueId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByBoutiqueId(@PathVariable Long boutiqueId) {
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbackByBoutiqueId(boutiqueId);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacksGroupedByBoutique() {
        List<FeedbackDTO> feedbacks = feedbackService.getAllFeedbacksGroupedByBoutique();
        return ResponseEntity.ok(feedbacks);
    }
    @GetMapping("/has-feedback")
    public ResponseEntity<Boolean> hasUserGivenFeedback(
            @RequestParam Long boutiqueId,
            @RequestParam Long utilisateurId) {
        boolean hasGivenFeedback = feedbackService.hasUserGivenFeedback(boutiqueId, utilisateurId);
        return ResponseEntity.ok(hasGivenFeedback);
    }
    @PutMapping("/{feedbackId}")
    public ResponseEntity<FeedbackDTO> updateFeedback(
            @PathVariable Long feedbackId,
            @RequestParam int rating,
            @RequestParam(required = false) String comment) {
        FeedbackDTO updatedFeedback = feedbackService.updateFeedback(feedbackId, rating, comment);
        return ResponseEntity.ok(updatedFeedback);
    }
}