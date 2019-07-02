package com.shenhufei.Katyusha.utils;

import java.lang.reflect.Method;

import com.shenhufei.Katyusha.model.Methods;

public class DataUtils {
    public static Methods getMethods(Method method, Integer code,
            String version, String className) {
        Methods methodss = new Methods();
        methodss.setVersionMethodCode(code + "_" + version);
        methodss.setVersion(version);
        methodss.setCode(code);
        String[] split = className.split("\\.");
        methodss.setClassName(StringUtils.getBeanName(split[split.length - 1]));
        methodss.setFullClassName(className);
        // 获取方法名称
        methodss.setMethodName(method.getName());
        // 获取方法返回值
        methodss.setReturnType(method.getReturnType());
        // 获取方法参数列表
        methodss.setParameters(method.getParameters());
        // 获取方法的参数的个数
        methodss.setParameterCount(method.getParameterCount());
        // 获取参数的类型的字节码文件
        methodss.setParameterTypes(method.getParameterTypes());
        return methodss;
    }
}
