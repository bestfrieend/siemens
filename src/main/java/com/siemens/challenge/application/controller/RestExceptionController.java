package com.siemens.challenge.application.controller;

import com.siemens.challenge.application.enums.ResultCodeEnum;
import com.siemens.challenge.application.response.CommonResponse;
import com.siemens.challenge.application.response.ResultBuilderUtil;
import com.siemens.challenge.platform.exceptions.InvalidPropertiesException;
import com.siemens.challenge.platform.exceptions.MissingResourceException;
import com.siemens.challenge.platform.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class RestExceptionController {

    @ExceptionHandler
    public CommonResponse<?> handle(Throwable e) {
        log.error("Error: ", e);
        return ResultBuilderUtil.buildFailure(ResultCodeEnum.INTERAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public CommonResponse<?> handle(IllegalArgumentException e) {
        log.debug("Error: ", e.getMessage());
        return ResultBuilderUtil.buildFailure(ResultCodeEnum.STATUS_BAD_REQUEST, Utility.getStackTrace(e));
    }

    @ExceptionHandler
    public CommonResponse<?> handle(InvalidPropertiesException e) {
        log.debug("Error: InvalidPropertiesException", e);
        return ResultBuilderUtil.buildFailure(ResultCodeEnum.STATUS_BAD_REQUEST, Utility.getStackTrace(e));
    }

    @ExceptionHandler
    public CommonResponse<?> handle(MissingResourceException e) {
        log.debug("Error: MissingResourceException", e);
        return ResultBuilderUtil.buildFailure(ResultCodeEnum.STATUS_NOT_FOUND, Utility.getStackTrace(e));
    }


}
