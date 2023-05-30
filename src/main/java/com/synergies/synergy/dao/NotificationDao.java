package com.synergies.synergy.dao;

import com.synergies.synergy.domain.dto.NotificationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationDao {
    List<NotificationDto> selectNotificationList();

    int insertNotification(NotificationDto notification);

    int updateNotification(NotificationDto notification);

    int deleteNotification(int id);
}
