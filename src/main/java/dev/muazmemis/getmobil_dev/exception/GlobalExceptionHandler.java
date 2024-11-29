package dev.muazmemis.getmobil_dev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorMessage> handleValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        ErrorMessage error = ErrorMessage.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST)
                .error(errors.toString())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleInventoryException(InsufficientStockException ex, WebRequest request) {
        ErrorMessage error = ErrorMessage.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST)
                .error(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ItemNotFoundException ex, WebRequest request) {
        ErrorMessage error = ErrorMessage.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST)
                .error(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(Exception ex, WebRequest request) {
        ErrorMessage error = ErrorMessage.builder()
                .timestamp(new Date())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(error, error.getStatus());
    }

}
