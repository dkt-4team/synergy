package com.synergies.synergy.service;



import com.synergies.synergy.domain.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<NotificationDto> getAll();

    int insert(NotificationDto notification);

    int update(NotificationDto notification);

    int delete(int id);
}
