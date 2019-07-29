package com.zym.springcloud.user.center.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zhongym
 * @date 2017/7/28
 */
@ControllerAdvice
public class ExceptionAdvice {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Object handleException(Throwable ex, HttpServletRequest request) {
        log.error("url={} ,exceptionMessage:{}", request.getRequestURI(), ex.getMessage(), ex);

        if (ex instanceof MethodArgumentTypeMismatchException){

        }
        if (ex instanceof MissingServletRequestParameterException){

        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("desc", ex.getMessage());
        return map;
    }
}
