package com.shenhufei.enums;

/**
 *   
 * 
 * @Description: 分表的拆分方式
 * @author shenhufei  
 * @date 2019年4月30日 下午2:08:07 
 * @version V1.0   
 */
public enum DivideTacticsEnum {
    NUMBERDIVIDE(1, "数字类型的字段根据取模进行拆分"), STRINGDIVIDE(2, "按照字符串进行拆分");
    private Integer code;
    private String desc;

    private DivideTacticsEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    DivideTacticsEnum() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}