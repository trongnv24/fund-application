package aibles.java.spring.boot.fund.controller;

import aibles.java.spring.boot.fund.exception.ExceptionResponse;
import aibles.java.spring.boot.fund.exception.ResourceNotFoundException;
import aibles.java.spring.boot.fund.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
        @ExceptionHandler(UserAlreadyExistsException.class)
        public final ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    LocalDateTime.now(),
                    ex.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    LocalDateTime.now(),
                    ex.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                    LocalDateTime.now(),
                    ex.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                errors.toString()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}