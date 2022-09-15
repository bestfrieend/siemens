package com.siemens.challenge.application.response;

public class ErrorResponse<T> extends CommonResponse {

    public ErrorResponse(int status, String code, String message, T data) {
        super(status, code, message, data);
    }
}
