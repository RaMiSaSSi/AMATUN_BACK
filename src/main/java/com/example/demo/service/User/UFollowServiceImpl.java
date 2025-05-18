package com.example.demo.service.User;

        import com.example.demo.dto.FollowDTO;
        import com.example.demo.model.Follow;
        import com.example.demo.repository.FollowRepository;
        import com.example.demo.repository.UtilisateurInscritRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.stream.Collectors;

        @Service
        public class UFollowServiceImpl implements UFollowService {

            @Autowired
            private UtilisateurInscritRepository utilisateurInscritRepository;
            @Autowired
            private FollowRepository followRepository;

            @Override
            public List<FollowDTO> getAllFollows() {
                List<Follow> follows = followRepository.findAll();
                return follows.stream()
                        .map(follow -> {
                            FollowDTO dto = new FollowDTO();
                            dto.setBoutiqueId(follow.getBoutique().getId());
                            dto.setUserId(follow.getUtilisateur().getId());
                            return dto;
                        })
                        .collect(Collectors.toList());
            }

            @Override
            public List<FollowDTO> getFollowsByUserId(Long utilisateurId) {
                List<Follow> follows = followRepository.findByUtilisateurId(utilisateurId);
                return follows.stream()
                        .map(follow -> {
                            FollowDTO dto = new FollowDTO();
                            dto.setBoutiqueId(follow.getBoutique().getId());
                            dto.setUserId(follow.getUtilisateur().getId());
                            return dto;
                        })
                        .collect(Collectors.toList());
            }
        }