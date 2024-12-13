package com.dfcold.whulibbackend.common.advice;

import com.dfcold.whulibbackend.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dfcold
 * 这个是到达DispatcherServlet之后分发给Controller的时候进行的过滤
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleException(Exception ex) {
        // 获取当前请求信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 打印请求信息
        log.error("An error occurred: {}", ex.getMessage());
        log.error("Request URL: {}", request.getRequestURL());
        log.error("Request Method: {}", request.getMethod());
        log.error("Request Params: {}", request.getQueryString());

        // 创建 Result 对象并设置相关信息
        return Result.error()
                .message("内部服务器错误");
    }
}