package com.shenhufei.enums;
/**
 *   
 * 
 * @Description: 配置文件中标签对应的属性
 * @author shenhufei  
 * @date 2019年4月30日 下午2:08:07 
 * @version V1.0   
 */
public enum DivideConfigAttributeEnum {
	pojo("pojo"), name("name"), part("part");
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private DivideConfigAttributeEnum(String type) {
        this.type = type;
    }
}
