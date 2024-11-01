package com.sandbox.todos.controller;

import com.sandbox.todos.domain.ResponseMessage;
import com.sandbox.todos.domain.Todo;
import com.sandbox.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
@CrossOrigin("*")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<ResponseMessage> getTodos() {
        List<Todo> todos = todoService.findAll();
        return ResponseEntity.ok(new ResponseMessage("정상적으로 요청되었습니다.", todos));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createTodo(@RequestBody Todo todo) {
        todo.setCompleted(false);
        Todo savedTodo = todoService.save(todo);
        return ResponseEntity.ok(new ResponseMessage(savedTodo.getId() + "의 todo가 생성되었습니다."));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ResponseMessage> deleteTodo(@PathVariable String todoId) {
        todoService.delete(todoId);
        return ResponseEntity.ok(new ResponseMessage(todoId + "의 todo가 삭제되었습니다."));
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<ResponseMessage> toggleTodoCompletion(@PathVariable String todoId) {
        Todo existingTodo = todoService.findById(todoId);
        if (existingTodo == null) {
            return ResponseEntity.status(404).body(new ResponseMessage("정상적이지 않은 요청입니다."));
        }

        existingTodo.setCompleted(!existingTodo.getCompleted());
        Todo updatedTodo = todoService.update(existingTodo);
        return ResponseEntity.ok(new ResponseMessage(updatedTodo.getId() + "의 completed가 정상적으로 토글되었습니다."));
    }
}
