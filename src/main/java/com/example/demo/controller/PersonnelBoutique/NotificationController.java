package com.example.demo.controller;

            import com.example.demo.model.Notification;
            import com.example.demo.repository.NotificationRepository;
            import org.springframework.messaging.simp.SimpMessagingTemplate;
            import org.springframework.web.bind.annotation.*;

            import java.util.List;

            @RestController
            @RequestMapping("/notifications")
            public class NotificationController {

                private final NotificationRepository notificationRepository;

                public NotificationController(NotificationRepository notificationRepository) {
                    this.notificationRepository = notificationRepository;
                }

                @GetMapping("/personnel/{personnelId}")
                public List<Notification> getNotificationsByPersonnel(@PathVariable Long personnelId) {
                    return notificationRepository.findByPersonnelBoutiqueId(personnelId);
                }
                @GetMapping("/count/pending")
                public long getCountOfPendingNotifications() {
                    return notificationRepository.countNotificationsWithPendingCommandes();
                }
            }