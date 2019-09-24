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
}
