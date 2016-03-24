package com.easyframework.webservice.restfulclient.annotation;

import com.easyframework.webservice.restfulclient.model.Action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface Filter {
    Action value() default Action.BEFORE_ENCODE;

}
