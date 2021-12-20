package com.endyary.perlibserver.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles application wide exceptions
 *
 * @author Nenad Dramicanin
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String DEFAULT_ERROR_MESSAGE = "Error while processing the request!";

    // A record holding a response message
    private record ErrorResponse(String message) {
    }

    /**
     * Handles all exceptions - creates a response object and logs the exception
     *
     * @param exception - the occurred exception
     * @return a proper HTTP response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception exception) {
        logger.error(DEFAULT_ERROR_MESSAGE, exception);
        ErrorResponse response = new ErrorResponse(DEFAULT_ERROR_MESSAGE);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles a validation exception (MethodArgumentNotValidException).
     * Response contains key value pairs of a field name and its validation message
     *
     * @param exception - the occurred exception
     * @return a proper HTTP response
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles a validation exception (ConstraintViolationException).
     * Response contains key value pairs of a field name and its validation message
     *
     * @param exception - the occurred exception
     * @return a proper HTTP response
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Map<String, String>> handleConstraintValidationExceptions(
            ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach((error) -> {
            String fieldName = error.getPropertyPath().toString();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
