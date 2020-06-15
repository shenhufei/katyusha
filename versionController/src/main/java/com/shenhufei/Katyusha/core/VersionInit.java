package com.shenhufei.Katyusha.core;

import java.util.List;

public interface VersionInit {
	/**
	 * 提供初始化方法
	 * @author shenhufei
	 *
	 * @throws Exception
	 */
	void  initMethodMap(List<Class<?>> list, List<String> listString)throws Exception;
	/**
	 * 加载前置，后置，环绕方法
	 * @author shenhufei
	 *
	 * @param list
	 * @param listString
	 * @throws Exception
	 */
	void initBeforeAfterAroundMethods() throws  Exception;
}
