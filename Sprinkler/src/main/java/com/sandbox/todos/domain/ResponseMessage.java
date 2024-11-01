package com.sandbox.todos.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseMessage {
    private String message;
    private List<Todo> todos;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(String message, List<Todo> todos) {
        this.message = message;
        this.todos = todos;
    }
}
