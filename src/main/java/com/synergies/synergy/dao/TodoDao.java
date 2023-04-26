package com.synergies.synergy.dao;

import com.synergies.synergy.domain.dto.TodoDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoDao {

    List<TodoDto> getAll();

    int insert(TodoDto todo);

    int update(TodoDto todo);

    int delete(int id);
}
