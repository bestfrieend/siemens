package com.siemens.challenge.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @param <T>
 * @author Wasif
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    protected Integer status;
    protected String code;
    protected String message;
    protected T data;
}
