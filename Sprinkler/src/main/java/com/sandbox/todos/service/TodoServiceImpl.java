package com.sandbox.todos.service;

import com.sandbox.todos.domain.Todo;
import com.sandbox.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    TodoRepository repository;

    @Override
    public List<Todo> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean save(String content) {
        Todo newTodo = new Todo();
        newTodo.setContent(content);

        return repository.save(newTodo);
    }

    @Override
    public boolean delete(String todo) {
        return repository.delete(todo);
    }

    @Override
    public boolean update(String id) {
        return repository.update(id);
    }
}
