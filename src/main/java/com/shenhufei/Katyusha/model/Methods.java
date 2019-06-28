package com.shenhufei.Katyusha.model;

import java.lang.reflect.Parameter;

import lombok.Data;

/**
 *   
 * 
 * @Description:
 * @author shenhufei  
 * @date 2019年6月13日 上午11:09:29 
 * @version V1.0   
 */
@Data
public class Methods {
    /**
     * 版本号
     */
    private String version;
    /**
     * 类名小写：demo Student类对应的是 student
     */
    private String className;

    /**
     * 类的全路径
     */
    private String fullClassName;

    /**
     * 版本信息和接口code值拼接
     */
    private String versionMethodCode;
    /**
     * 接口的编码
     */
    private Integer code;

    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法的参数
     */
    private Object param;
    /**
     * 参数类型
     */
    private Parameter[] parameters;

    /**
     * 方法返回值
     */
    private Class<?> returnType;

    /**
     * 参数个数
     */
    private Integer parameterCount;

    /**
     * 参数列表对应的字节码文件
     */
    private Class<?>[] parameterTypes;

}
