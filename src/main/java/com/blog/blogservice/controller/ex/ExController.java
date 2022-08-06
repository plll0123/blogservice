package com.blog.blogservice.controller.ex;

import com.blog.blogservice.controller.ex.custom.CustomReflection;
import com.blog.blogservice.exception.BlogException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExController {

    private final CustomReflection customReflection;

    @ExceptionHandler(BlogException.class)
    public ModelAndView blogExceptionHandler(BlogException e){
        return new ModelAndView(e.getView())
                .addObject(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ModelAndView bindExceptionHandler(BindingResult e, HandlerMethod hm) {


        String viewPath = customReflection.getViewOfValidDto(hm);


        ModelAndView mav = new ModelAndView(viewPath);

        for (FieldError fieldError : e.getFieldErrors()) {
            mav.addObject(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return mav;

    }

}
