package com.shenhufei.enums;

/**
 * @Description: 数据库数据类型
 * @author shenhufei  
 * @date 2019年5月13日 下午5:50:49 
 * @version V1.0   
 */
public enum DataBaseDataTypeEnum {
    DECIMAL("DECIMAL"), DateE("DATETIME"), BITE("BIT");
    private String type;

    private DataBaseDataTypeEnum(String type) {

        this.type = type;
    }

    DataBaseDataTypeEnum() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
