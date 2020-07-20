package com.shenhufei.pojo;

import lombok.Data;

/**
 * @Description: 分表信息管理表对应的实体类
 * @author shenhufei  
 * @date 2019年4月30日 下午2:46:16 
 * @version V1.0   
 */
@Data
public class DivideTableManagerConfigPojo {
    // 实体类全路径
    private String pojoName;
    // id
    private Long id;
    // 分几张表
    private Integer part;
    // 表名
    private String tableName;
    // 描述
    private String des;
    // 每次取的行数
    private Integer step;



}
