package xgt.easy.webservice.annotation;

import xgt.easy.webservice.model.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface Filter {
    Action value() default Action.BEFORE_ENCODE;

}
