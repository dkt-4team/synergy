package com.synergies.synergy.dao;

import com.synergies.synergy.domain.dto.TodoDeleteRequestDto;
import com.synergies.synergy.domain.dto.TodoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoDao {

    List<TodoDto> selectAllTodo(byte[] refUserId);

    int insertTodo(TodoDto todo);

    int updateTodo(TodoDto todo);

    int deleteTodo(TodoDeleteRequestDto todoDeleteRequestDTO);
}
