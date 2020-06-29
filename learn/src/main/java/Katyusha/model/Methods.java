package Katyusha.model;

import lombok.Data;

import java.lang.reflect.Parameter;

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

    public String getVersion() {
        return version;
    }

    public String getClassName() {
        return className;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public String getVersionMethodCode() {
        return versionMethodCode;
    }

    public Integer getCode() {
        return code;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object getParam() {
        return param;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public Integer getParameterCount() {
        return parameterCount;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public void setVersionMethodCode(String versionMethodCode) {
        this.versionMethodCode = versionMethodCode;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public void setParameterCount(Integer parameterCount) {
        this.parameterCount = parameterCount;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
