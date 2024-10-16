package com.bupjangsa.exception;

import com.bupjangsa.common.AppResponse;
import com.bupjangsa.common.ResponseCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<AppResponse<Void>> handleException(Exception ex) {
        log.error("handleException", ex);
        AppResponse<Void> response = AppResponse.responseFail(ResponseCodes.UNKNOWN_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorizeException.class)
    protected ResponseEntity<AppResponse<Void>> handleAuthorizeException(Exception ex) {
        log.error("AuthorizeException: {}", ex.getMessage());
        AppResponse<Void> response = AppResponse.responseFailMsg(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserDataException.class)
    protected ResponseEntity<AppResponse<Void>> handleUserDataException(Exception ex) {
        log.error("UserDataException: {}", ex.getMessage());
        AppResponse<Void> response = AppResponse.responseFailMsg(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
