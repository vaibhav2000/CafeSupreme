package com.vab.CafeSupreme.exceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CafeSupremeExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public String handleException(Exception e, Model m)
    {
        return "errorpage.html";
    }
}
