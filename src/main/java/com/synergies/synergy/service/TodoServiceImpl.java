package com.synergies.synergy.service;

import com.synergies.synergy.dao.TodoDao;
import com.synergies.synergy.domain.dto.TodoDeleteRequestDto;
import com.synergies.synergy.domain.dto.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public int createTodo(TodoDto todo) {
        return todoDao.insertTodo(todo);
    }

    @Override
    public List<TodoDto> readAllTodo(byte[] refUserId) {
        return todoDao.selectAllTodo(refUserId);
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
