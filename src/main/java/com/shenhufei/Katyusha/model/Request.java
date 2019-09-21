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

}
