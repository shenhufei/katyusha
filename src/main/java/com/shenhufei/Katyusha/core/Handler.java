package com.shenhufei.Katyusha.core;

import com.shenhufei.Katyusha.model.Request;

/**
 *  提供执行的接口
 * @date 2019年9月20日  
 * @version 1.0  
 * @author shenhufei
 */
public interface Handler {
	
	/**
	 * 提供执行的方法
	 * @author shenhufei
	 *
	 * @param param
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Object doHandler(Request param) throws ClassNotFoundException;
}
