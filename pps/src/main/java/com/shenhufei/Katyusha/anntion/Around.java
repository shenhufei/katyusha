package com.shenhufei.Katyusha.anntion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法的 环绕执行方法
 * @date 2019年9月24日  
 * @version 1.0  
 * @author shenhufei
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Around {
	public String[] value() default { "" };
}
