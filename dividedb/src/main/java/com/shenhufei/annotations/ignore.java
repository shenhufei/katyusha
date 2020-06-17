package com.shenhufei.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
 * @Description: 标记字段，和数据库字段没有映射关系；
 * @author shenhufei  
 * @date 2019年7月30日 下午5:57:55 
 * @version V1.0   
*/
@Documented
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ignore {

}
