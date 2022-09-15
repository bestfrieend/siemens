package com.siemens.challenge.application.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.challenge.application.enums.ResultCodeEnum;
import com.siemens.challenge.application.response.CommonResponse;
import com.siemens.challenge.application.response.ResultBuilderUtil;
import com.siemens.challenge.platform.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Wasif
 */
@Component
@Aspect
@Slf4j
public class CommonAspectController {
    private final ObjectMapper mapper = new ObjectMapper();

    @Pointcut("execution(* com.siemens.challenge.application.controller..*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) {
        Object o = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String ipAddr = getIpAddress(request);
        String url = request.getRequestURL().toString();
        String reqParam = preHandle(pjp, request);
        String path = request.getRequestURI();
        String servletPath = request.getServletPath();
        String errorMsg = "";
        try {
            log.debug("Request source IP: [{}], request cookies: [{}], request URL: [{}], request parameter: [{}], request",
                    ipAddr, mapper.writeValueAsString(request.getCookies()), url, reqParam);
            log.debug("RequestURI/path={}, ServletPath={}", path, servletPath);
            o = pjp.proceed(pjp.getArgs());
            // Can cast all objects to our specific common response type in order to avoid in consistency
            if (o instanceof CommonResponse) {
                return o;
            } else {
                return ResultBuilderUtil.buildSuccess(o);
            }
//            return o;
        } catch (Exception e) {
            errorMsg = e.getMessage();
            log.error(Utility.getStackTrace(e));
            return ResultBuilderUtil.buildFailure(ResultCodeEnum.INTERAL_SERVER_ERROR, errorMsg);
        } catch (Throwable e) {
            errorMsg = e.getMessage();
            e.printStackTrace();
            return ResultBuilderUtil.buildFailure(ResultCodeEnum.INTERAL_SERVER_ERROR, errorMsg);
        }
    }

    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        log.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        ip = request.getRemoteAddr();
        return ip;
    }

    /**
     * Input data
     *
     * @param joinPoint
     * @param request
     * @return
     */
    private String preHandle(ProceedingJoinPoint joinPoint, HttpServletRequest request) {

        String reqParam = "";
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return reqParam;
    }

    private Map<String, Integer> systemOperationLogsMap = null;
}