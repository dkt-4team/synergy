package com.synergies.synergy.service;


import com.synergies.synergy.domain.dto.TodoDeleteRequestDto;
import com.synergies.synergy.domain.dto.TodoDto;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public interface TodoService {
    List<TodoDto> getAll(String userId);

    int insert(TodoDto todo);

    int update(TodoDto todo);

    int delete(TodoDeleteRequestDto todoDeleteRequestDTO);
}
