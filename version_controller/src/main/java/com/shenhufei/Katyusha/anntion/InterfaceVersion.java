package com.shenhufei.Katyusha.anntion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口版本号，在接口的实现类上标注，标注此接口的类，表明该接口下所有方法
 * 的版本号和接口的版本号相同，如果和MethodVersion搭配使用，那么MethodVersion
 * 的版本不得低于InterfaceVersion 的版本。
 * @date 2020年6月30日
 * @version 1.0
 * @author shenhufei
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InterfaceVersion {
    String  value() default "";
}
