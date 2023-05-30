package com.synergies.synergy.service;


import com.synergies.synergy.domain.dto.TodoDeleteRequestDto;
import com.synergies.synergy.domain.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TodoService {
    List<TodoDto> readAllTodo(byte[] refUserId);

    int createTodo(TodoDto todo);

    int updateTodo(TodoDto todo);

    int deleteTodo(TodoDeleteRequestDto todoDeleteRequestDTO);
}
