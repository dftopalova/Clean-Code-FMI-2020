package com.telebeer.beertag.utilities;

import com.telebeer.beertag.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //timestamp information
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static Date date = new Date();
    private static String timeNow = dateFormat.format(date);

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleException(IllegalArgumentException ex, WebRequest request) {
        if (ex instanceof ObjectNotFoundException) {
            return handleExceptionHelper((ObjectNotFoundException) ex, request);
        }

        if (ex instanceof NoContentException) {
            return handleExceptionHelper((NoContentException) ex, request);
        }

        if (ex instanceof MalformedRequestException) {
            return handleExceptionHelper((MalformedRequestException) ex, request);
        }

        if (ex instanceof CollisionException) {
            return handleExceptionHelper((CollisionException) ex, request);
        }

        throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, ex.getMessage());
    }

    private ResponseEntity<Object> handleExceptionHelper(ObjectNotFoundException ex, WebRequest request) {
        String bodyOfResponse = generateResponseBody(HttpStatus.NOT_FOUND, ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<Object> handleExceptionHelper(MalformedRequestException ex, WebRequest request) {
        String bodyOfResponse = generateResponseBody(HttpStatus.BAD_REQUEST, ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> handleExceptionHelper(NoContentException ex, WebRequest request) {
        String bodyOfResponse = generateResponseBody(HttpStatus.NO_CONTENT, ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> handleExceptionHelper(CollisionException ex, WebRequest request) {
        String bodyOfResponse = generateResponseBody(HttpStatus.CONFLICT, ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private String generateResponseBody(HttpStatus status, String errorMessage) {
        return String.format("{\n" +
                        "\"timestamp\": \"%s\",\n" +
                        "\"status\": \"%d\",\n" +
                        "\"error\": \"%s\",\n" +
                        "\"message\": \"%s\"\n" +
                        "}"
                , timeNow, status.value(), status.getReasonPhrase(), errorMessage);
    }

}
