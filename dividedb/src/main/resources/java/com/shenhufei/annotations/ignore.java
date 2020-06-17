package com.shenhufei.annotations;

import java.lang.annotation.*;

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
