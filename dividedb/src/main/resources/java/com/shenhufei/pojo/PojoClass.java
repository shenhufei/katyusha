package com.shenhufei.pojo;

import lombok.Data;

/**
 *   
 * 
 * @Description:
 * @author shenhufei  
 * @date 2019年5月6日 上午9:58:16 
 * @version V1.0   
 */
@Data
public class PojoClass {
    /**
     * 具体的值
     */
    private Object value;
    /**
     * 字段的数据类型
     */
    private Object type;
    /**
     * 字段名称
     */
    private String name;

}
