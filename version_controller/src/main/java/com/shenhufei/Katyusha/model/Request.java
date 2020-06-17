package com.shenhufei.Katyusha.model;

import lombok.Data;

@Data
public class Request {
    /**
     * 版本信息
     */
    private String version;
    /**
     * 接口code值
     */
    private Integer code;
    /**
     * 接口参数
     */
    private Object obj;

    public String getVersion() {
        return version;
    }

    public Integer getCode() {
        return code;
    }

    public Object getObj() {
        return obj;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
