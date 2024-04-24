package com.owoidoho.bassey.swapigateway.interfaces.api;

import com.owoidoho.bassey.swapigateway.core.repository.ResourceNotFoundException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SWAPIControllerExceptionHandler {

  @ResponseBody
  @ExceptionHandler(ResourceNotFoundException.class)
  ResponseEntity<String> resourceNotFoundHandler(ResourceNotFoundException ex) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(ex.getResponse().toString(), headers, HttpStatus.NOT_FOUND);
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  ResponseEntity<String> genericExceptionHandler(Exception ex) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(
        new JSONObject()
            .put("detail", ex.getMessage())
            .toString(),
        headers,
        HttpStatus.INTERNAL_SERVER_ERROR
    );
  }
}
