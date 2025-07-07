package com.example.demo.service.User;

        import com.example.demo.dto.FeedbackDTO;
        import com.example.demo.model.Boutique;
        import com.example.demo.model.Feedback;
        import com.example.demo.model.UtilisateurInscrit;
        import com.example.demo.repository.BoutiqueRepository;
        import com.example.demo.repository.FeedbackRepository;
        import com.example.demo.repository.UtilisateurInscritRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.stream.Collectors;

        @Service
        public class FeedbackServiceImpl implements FeedbackService {

            @Autowired
            private FeedbackRepository feedbackRepository;

            @Autowired
            private BoutiqueRepository boutiqueRepository;

            @Autowired
            private UtilisateurInscritRepository utilisateurInscritRepository;

            @Override
            public FeedbackDTO addFeedback(Long boutiqueId, Long utilisateurId, int rating, String comment) {
                FeedbackDTO dto = new FeedbackDTO();
                dto.setBoutiqueId(boutiqueId);
                dto.setUtilisateurId(utilisateurId);
                dto.setRating(rating);
                dto.setComment(comment);

                Feedback feedback = toEntity(dto);
                Feedback savedFeedback = feedbackRepository.save(feedback);
                return toDTO(savedFeedback);
            }

            @Override
            public List<FeedbackDTO> getFeedbackByBoutiqueId(Long boutiqueId) {
                List<Feedback> feedbacks = feedbackRepository.findByBoutiqueId(boutiqueId);
                return feedbacks.stream().map(this::toDTO).collect(Collectors.toList());
            }

            @Override
            public List<FeedbackDTO> getAllFeedbacksGroupedByBoutique() {
                List<Feedback> feedbacks = feedbackRepository.findAll();
                return feedbacks.stream().map(this::toDTO).collect(Collectors.toList());
            }
            @Override
            public boolean hasUserGivenFeedback(Long boutiqueId, Long utilisateurId) {
                return feedbackRepository.existsByBoutiqueIdAndUtilisateurId(boutiqueId, utilisateurId);
            }
            @Override
            public FeedbackDTO updateFeedback(Long feedbackId, int rating, String comment) {
                Feedback feedback = feedbackRepository.findById(feedbackId)
                        .orElseThrow(() -> new IllegalArgumentException("Feedback not found"));

                feedback.setRating(rating);
                feedback.setComment(comment);
                Feedback updatedFeedback = feedbackRepository.save(feedback);

                return toDTO(updatedFeedback);
            }

            private FeedbackDTO toDTO(Feedback feedback) {
                FeedbackDTO dto = new FeedbackDTO();
                dto.setId(feedback.getId());
                dto.setBoutiqueId(feedback.getBoutique().getId());
                dto.setUtilisateurId(feedback.getUtilisateur().getId());
                dto.setRating(feedback.getRating());
                dto.setComment(feedback.getComment());
                return dto;
            }

            private Feedback toEntity(FeedbackDTO dto) {
                Boutique boutique = boutiqueRepository.findById(dto.getBoutiqueId())
                        .orElseThrow(() -> new IllegalArgumentException("Boutique not found"));
                UtilisateurInscrit utilisateur = utilisateurInscritRepository.findById(dto.getUtilisateurId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

                Feedback feedback = new Feedback();
                feedback.setBoutique(boutique);
                feedback.setUtilisateur(utilisateur);
                feedback.setRating(dto.getRating());
                feedback.setComment(dto.getComment());
                return feedback;
            }
        }