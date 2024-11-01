package com.sandbox.todos.service;

import com.sandbox.todos.domain.Todo;

import java.util.List;

public interface TodoService {

    public List<Todo> findAll();

    public boolean save(String content);

    public boolean delete(String todo);

    public boolean update(String id);
}
