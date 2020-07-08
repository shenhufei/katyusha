package com.shenhufei.exception;

import lombok.Data;

/**
 *   
 * 
 * @Description: 读取分表配置文件,如果找不到该配置文件，那么就抛出此异常 数据库表对应的实体类的时候抛出该一场；
 * @author shenhufei  
 * @date 2019年5月1日 下午21:34:07 
 * @version V1.0   
 */
@Data
public class DivideDbConfigFileNotFoundException
        extends ClassNotFoundException {
    private static final long serialVersionUID = -5320987730537124713L;

    private String message;

    public DivideDbConfigFileNotFoundException() {
        super();
    }

    public DivideDbConfigFileNotFoundException(String s, Throwable ex) {
        super(s, ex);
    }

    public DivideDbConfigFileNotFoundException(String message) {
        super();
        this.message = message;
    }


}
