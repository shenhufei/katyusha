package com.shenhufei.Katyusha.core;

import java.util.List;

public interface VersionInit {
    /**
     * 提供初始化方法
     *
     * @throws Exception
     * @author shenhufei
     */
    void initMethodMap(List<Class<?>> list, List<String> listString) throws Exception;

    /**
     * 加载前置，后置，环绕方法
     *
     * @param list
     * @param listString
     * @throws Exception
     * @author shenhufei
     */
    void initBeforeAfterAroundMethods() throws Exception;
}
