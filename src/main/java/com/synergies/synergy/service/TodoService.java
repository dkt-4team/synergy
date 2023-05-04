package com.synergies.synergy.service;


import com.synergies.synergy.domain.dto.TodoDeleteRequestDto;
import com.synergies.synergy.domain.dto.TodoDto;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public interface TodoService {
    List<TodoDto> selectAllTodo(String userId);

    int insertTodo(TodoDto todo);

    int updateTodo(TodoDto todo);

    int deleteTodo(TodoDeleteRequestDto todoDeleteRequestDTO);
}
