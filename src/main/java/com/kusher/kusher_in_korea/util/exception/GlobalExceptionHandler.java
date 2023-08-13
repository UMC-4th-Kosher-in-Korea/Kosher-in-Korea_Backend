package com.kusher.kusher_in_korea.util.exception;

import com.kusher.kusher_in_korea.util.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {CustomException.class})
    // 예외 발생 시 클라이언트에게 전달할 응답을 생성하는 메소드
    public ResponseEntity<ApiResponse> handleCustomException(CustomException e, WebRequest request) {
        log.error("handleCustomException", e);
        return new ResponseEntity<>(ApiResponse.fail(e.getResponseCode()), HttpStatus.valueOf(e.getResponseCode().getHttpStatusCode()));
    }
}
