package com.devtask.integrator.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class CustomExceptionHandler {

    private class JsonResponse {
        String message;
        int status;

        public JsonResponse() {
        }

        public JsonResponse(String message, int status) {
            super();
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    @ExceptionHandler
    public ResponseEntity handleWebClientException(HttpServletRequest request, WebClientResponseException ex) {
        return new ResponseEntity<>(new JsonResponse(ex.getStatusText(), ex.getStatusCode().value()), ex.getStatusCode());
    }

}
