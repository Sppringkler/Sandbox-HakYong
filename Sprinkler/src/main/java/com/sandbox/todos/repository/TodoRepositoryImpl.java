package com.sandbox.todos.repository;

import com.sandbox.todos.domain.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepositoryImpl implements TodoRepository{
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
