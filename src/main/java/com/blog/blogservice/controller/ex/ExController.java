package com.blog.blogservice.controller.ex;

import com.blog.blogservice.exception.BlogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class ExController {

    @ExceptionHandler(BlogException.class)
    public ModelAndView blogExceptionHandler(BlogException e){
        return new ModelAndView(e.getView())
                .addObject(e.getMessage());
    }


}
