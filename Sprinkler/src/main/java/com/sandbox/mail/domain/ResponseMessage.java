package com.sandbox.mail.domain;

import com.sandbox.todos.domain.Todo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseMessage {
    private boolean isOk;

    public ResponseMessage(boolean isOk) {
        this.isOk = isOk;
    }
}
