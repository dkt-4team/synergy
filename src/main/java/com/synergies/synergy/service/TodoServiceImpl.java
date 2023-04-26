package com.synergies.synergy.service;

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
    public List<TodoDto> getAll() {
        return todoDao.getAll();
    }

    @Override
    public int insert(TodoDto todo) {
        return todoDao.insert(todo);
    }

    @Override
    public int update(TodoDto todo) {
        System.out.println("test");
        return todoDao.update(todo);
    }

    @Override
    public int delete(int id) {
        return todoDao.delete(id);
    }
}
