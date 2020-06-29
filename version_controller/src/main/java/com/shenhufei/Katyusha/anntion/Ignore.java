package com.shenhufei.Katyusha.anntion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 接口方法code代号
 * @date 2019年9月24日
 * @version 1.1
 * @author shenhufei
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ignore {
    String value() default "";
}
