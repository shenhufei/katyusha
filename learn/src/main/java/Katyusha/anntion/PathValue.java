package Katyusha.anntion;

import java.lang.annotation.*;

/**
 * 读取的配置文件的路径
 * @date 2019年9月24日  
 * @version 1.0  
 * @author shenhufei
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathValue {
	 public String value() default "";
}
