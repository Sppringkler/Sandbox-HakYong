package com.sandbox.todos.service;

import com.sandbox.todos.domain.Todo;

import java.util.List;

public interface TodoService {

    public List<Todo> findAll();

    public Todo save(Todo todo);

    public void delete(String id);

    public Todo update(Todo todo);

    Todo findById(String id);
}
