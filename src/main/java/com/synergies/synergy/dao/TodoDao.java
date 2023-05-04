package com.synergies.synergy.dao;

import com.synergies.synergy.domain.dto.TodoDeleteRequestDto;
import com.synergies.synergy.domain.dto.TodoDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoDao {

    List<TodoDto> selectAllTodo(String userId);

    int insertTodo(TodoDto todo);

    int updateTodo(TodoDto todo);

    int deleteTodo(TodoDeleteRequestDto todoDeleteRequestDTO);
}
