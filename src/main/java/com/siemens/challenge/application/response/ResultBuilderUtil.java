package com.siemens.challenge.application.response;

import com.siemens.challenge.application.enums.ResultCodeEnum;

/**
 * @author Wasif
 */
public class ResultBuilderUtil {

    public static CommonResponse buildSuccess(Object o) {
        return new CommonResponse(ResultCodeEnum.SUCCESS.getStatus(), ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), o);
    }

    public static CommonResponse buildSuccess(String message) {
        return new CommonResponse(ResultCodeEnum.SUCCESS.getStatus(), ResultCodeEnum.SUCCESS.getCode(), message, null);
    }

    public static ErrorResponse buildFailure(int status, String code, String message) {
        return new ErrorResponse(status, code, message, null);
    }

    public static ErrorResponse buildFailure(ResultCodeEnum resultCodeEnum) {
        return buildFailure(resultCodeEnum.getStatus(), resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    public static ErrorResponse buildFailure(ResultCodeEnum resultCodeEnum, String msg) {
        return buildFailure(resultCodeEnum.getStatus(), resultCodeEnum.getCode(), msg);
    }
}

