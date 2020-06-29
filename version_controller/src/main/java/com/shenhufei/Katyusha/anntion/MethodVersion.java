package com.shenhufei.Katyusha.anntion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 方法的版本号，在方法上使用该注解，
 * 1.如果一个添加了InterfaceVersion注解的类里面，如果方法上没有Ignore,也没有MethodVersion
 * 那么初始化的时候就会抛出该方法缺少版本异常；
 * 1.MethodVersion版本不得低于InterfaceVersion的版本，否则会抛出版本不兼容异常；
 * @date 2020年6月30日
 * @version 1.0
 * @author shenhufei
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodVersion {
    public String[] value() default { "" };
}
