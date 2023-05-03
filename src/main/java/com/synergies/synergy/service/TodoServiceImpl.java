package com.synergies.synergy.service;

import com.synergies.synergy.domain.dto.TodoDeleteRequestDTO;
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
    public List<TodoDto> getAll(String userId) {
        return todoDao.getAll(userId);
    }

    @Override
    public int insert(TodoDto todo) {
        return todoDao.insert(todo);
    }

    @Override
    public int update(TodoDto todo) {
        return todoDao.update(todo);
    }

    @Override
    public int delete(TodoDeleteRequestDTO todoDeleteRequestDTO) {
        return todoDao.delete(todoDeleteRequestDTO);
    }
}
