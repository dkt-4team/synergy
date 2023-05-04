package com.synergies.synergy.service;

import com.synergies.synergy.domain.dto.TodoDeleteRequestDto;
import com.synergies.synergy.domain.dto.TodoDto;
import com.synergies.synergy.dao.TodoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public List<TodoDto> selectAllTodo(String userId) {
        return todoDao.selectAllTodo(userId);
    }

    @Override
    public int insertTodo(TodoDto todo) {
        return todoDao.insertTodo(todo);
    }

    @Override
    public int updateTodo(TodoDto todo) {
        return todoDao.updateTodo(todo);
    }

    @Override
    public int deleteTodo(TodoDeleteRequestDto todoDeleteRequestDTO) {
        return todoDao.deleteTodo(todoDeleteRequestDTO);
    }
}
