package com.ongshop.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiException extends Exception {

    private final ErrorCode errorCode;
}
