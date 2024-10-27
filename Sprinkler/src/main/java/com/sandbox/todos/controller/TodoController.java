package com.sandbox.todos.controller;

import com.sandbox.todos.domain.ResponseMessage;
import com.sandbox.todos.domain.Todo;
import com.sandbox.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<ResponseMessage> getTodos(){
        List<Todo> todos = todoService.findAll();
        return ResponseEntity.ok(new ResponseMessage("정상적으로 요청되었습니다.",todos));
    }

    @PostMapping("todos")
    public ResponseEntity<ResponseMessage> createTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoService.save(todo);

        return ResponseEntity.ok(new ResponseMessage(savedTodo.getId() + "의 todo가 생성되었습니다."));
    }

}
