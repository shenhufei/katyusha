package Katyusha.anntion;

import java.lang.annotation.*;

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
