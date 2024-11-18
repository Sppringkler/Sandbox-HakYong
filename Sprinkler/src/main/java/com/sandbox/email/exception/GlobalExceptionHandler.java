package com.sandbox.email.exception;

import com.sandbox.email.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public String exception(Exception e) {
//        e.printStackTrace();
//        return ResponseEntity;
//    }
//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException ex) {
//        ErrorResponseDTO response = new ErrorResponseDTO(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//}
