package com.synergies.synergy.service;


import com.synergies.synergy.domain.dto.TodoDeleteRequestDto;
import com.synergies.synergy.domain.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TodoService {
    List<TodoDto> selectAllTodo(byte[] refUserId);

    int insertTodo(TodoDto todo);

    int updateTodo(TodoDto todo);

    int deleteTodo(TodoDeleteRequestDto todoDeleteRequestDTO);
}
