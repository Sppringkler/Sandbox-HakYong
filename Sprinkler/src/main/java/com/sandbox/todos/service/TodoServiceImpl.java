package com.sandbox.todos.service;

import com.sandbox.todos.domain.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    @Override
    public List<Todo> findAll() {
        return List.of();
    }

    @Override
    public Todo save(Todo todo) {
        return null;
    }

    @Override
    public void delete(Todo todo) {

    }

    @Override
    public Todo update(Todo todo) {
        return null;
    }
}
