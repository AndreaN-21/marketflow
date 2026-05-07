package com.marketflow.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", 404);
    body.put("error", "Not found");
    body.put("message", ex.getMessage());
    body.put("timestamp", Instant.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> fieldErrors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", 400);
    body.put("error", "Validation failed");
    body.put("fields", fieldErrors);
    body.put("timestamp", Instant.now());
    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", 409);
    body.put("error", "Data conflict");
    body.put("message", "A record with this data already exists");
    body.put("timestamp", Instant.now());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
  }
}
