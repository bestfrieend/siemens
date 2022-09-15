package com.siemens.challenge.application.controller;

import com.siemens.challenge.application.response.CommonResponse;
import com.siemens.challenge.platform.exceptions.InvalidPropertiesException;
import com.siemens.challenge.platform.exceptions.MissingResourceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class RestExceptionControllerTest {

    @InjectMocks
    RestExceptionController controller;

    @Test
    public void invalidPropertiesExceptionTest() {
        CommonResponse<?> entity = controller.handle(new InvalidPropertiesException("Invalid properties cant be parsed"));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), entity.getStatus());
    }

    @Test
    public void missingResourceException() {
        CommonResponse<?> entity = controller.handle(new MissingResourceException("Resource Not Found!"));
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), entity.getStatus());
    }

    @Test
    public void internalServerErrorTest() {
        CommonResponse<?> entity = controller.handle(new Throwable("Internal Server Error occured"));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), entity.getStatus());
    }

}
