package com.synergies.synergy.dao;

import com.synergies.synergy.domain.dto.NotificationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationDao {
    List<NotificationDto> getAll();

    int insert(NotificationDto notification);

    int update(NotificationDto notification);

    int delete(int id);
}
