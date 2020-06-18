package com.shenhufei.pojo;

import lombok.Data;

/**
 * @Description: 分表配置解析后实体类存储
 * @author shenhufei  
 * @date 2019年4月30日 下午2:46:16 
 * @version V1.0   
 */
// TODO 需要改造成单例设计模式；
@Data
public class DivideConfigPojo {
    private String dataDbName;
    private String pojoName;
    private String field;
    private Integer part;



   
}
