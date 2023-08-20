package com.ongshop.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SUCCESS(HttpStatus.OK, "OK"),

    NOT_SUPPORTED_HTTP_METHOD(HttpStatus.BAD_REQUEST, "지원하지 않는 Http Method 입니다."),
    NOT_VALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "처리 도중 오류가 발생하였습니다.");

    private final HttpStatus status;
    private final String message;
}
