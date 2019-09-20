package com.shenhufei.Katyusha.context;

import com.shenhufei.Katyusha.model.RequestParam;

/**
 *  提供执行的接口
 * @date 2019年9月20日  
 * @version 1.0  
 * @author shenhufei
 */
public interface Handler {
	
	public Object doHandler(RequestParam param) throws ClassNotFoundException;
}
