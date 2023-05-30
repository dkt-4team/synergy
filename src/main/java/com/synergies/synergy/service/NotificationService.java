package com.synergies.synergy.service;



import com.synergies.synergy.domain.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<NotificationDto> readNotificationList();

    int readNotification(NotificationDto notification);

    int updateNotification(NotificationDto notification);

    int deleteNotification(int id);
}
