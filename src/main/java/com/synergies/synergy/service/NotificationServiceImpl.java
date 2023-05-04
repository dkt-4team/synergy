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
    public List<NotificationDto> notificationList() {
        return notificationDao.selectNotificationList();
    }

    @Override
    public int notificationAdd(NotificationDto notification) {
        return notificationDao.insertNotification(notification);
    }

    @Override
    public int notificationModify(NotificationDto notification) {
        return notificationDao.updateNotification(notification);
    }

    @Override
    public int notificationRemove(int id) {
        return notificationDao.deleteNotification(id);
    }

}
