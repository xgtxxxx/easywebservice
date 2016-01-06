package xgt.easy.webservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface Skip {
    boolean skipIfEmpty() default true;
    String skipIfExist() default "";
    String skipIfMatch() default "";
}
