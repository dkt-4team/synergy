package com.synergies.synergy.service;

import com.synergies.synergy.dao.NotificationDao;
import com.synergies.synergy.domain.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    @Override
    public List<NotificationDto> getAll() {
        return notificationDao.getAll();
    }



}
