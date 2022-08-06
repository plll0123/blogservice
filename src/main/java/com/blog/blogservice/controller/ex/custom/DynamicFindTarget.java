package com.blog.blogservice.controller.ex.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

@Slf4j
@Component
public class DynamicFindTarget implements CustomReflection {

    @Override
    public String findView(HandlerMethod hm) {
        try {
            Class<?> type = hm.getMethod().getParameters()[0].getType();

            Object targetObj = Class.forName(type.getName())
                    .getConstructor()
                    .newInstance();

            Method getExOccursView = type.getMethod("getExOccursView");

            System.out.println("getExOccursView.invoke(targetObj).toString(); = " + getExOccursView.invoke(targetObj).toString());

            return getExOccursView.invoke(targetObj).toString();
        } catch (ReflectiveOperationException e) {
            log.info("Throw ReflectiveException By {}", this.getClass(), e);
            return "redirect:/";
        }
    }
}
