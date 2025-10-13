package com.test.shop.Product.Exception;

import java.time.Instant;
import java.util.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(NotFoundException.class)
public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex, WebRequest req) {
    return build(HttpStatus.NOT_FOUND, ex.getMessage(), req);
}


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex, WebRequest req) {
    Map<String, Object> body = base(HttpStatus.BAD_REQUEST, "Validation failed", req);
    Map<String, String> errors = new HashMap<>();
    for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
    errors.put(fe.getField(), fe.getDefaultMessage());
}
body.put("errors", errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
}


@ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<Map<String, Object>> handleConstraint(ConstraintViolationException ex, WebRequest req) {
    return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
}


@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<Map<String, Object>> handleIntegrity(DataIntegrityViolationException ex, WebRequest req) {
    return build(HttpStatus.CONFLICT, "Operation violates data integrity (likely FK constraint)", req);
}


@ExceptionHandler(Exception.class)
public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex, WebRequest req) {
    return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), req);
}


private ResponseEntity<Map<String, Object>> build(HttpStatus status, String message, WebRequest req) {
Map<String, Object> body = base(status, message, req);
    return ResponseEntity.status(status).body(body);
}


private Map<String, Object> base(HttpStatus status, String message, WebRequest req) {
Map<String, Object> body = new LinkedHashMap<>();
body.put("timestamp", Instant.now().toString());
body.put("status", status.value());
body.put("error", status.getReasonPhrase());
body.put("message", message);
body.put("path", req.getDescription(false));
return body;
}
}
