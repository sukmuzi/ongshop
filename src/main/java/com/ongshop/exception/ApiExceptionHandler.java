package com.ongshop.exception;

import com.ongshop.api.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        ErrorResponse res = new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().name(), e.getErrorCode().getMessage());

        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResponse<?> validException(MethodArgumentNotValidException e) {
        return ApiResponse.createFail(e);
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ApiResponse<?> illegalStateException(IllegalStateException e) {
        return ApiResponse.createError(e.getMessage());
    }
}
