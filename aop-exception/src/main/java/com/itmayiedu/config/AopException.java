package com.itmayiedu.config;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AopException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String aopException(){

        return "使用到了异常处理AOP方法~";

    }


}


