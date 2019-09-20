package com.shenhufei.Katyusha.filter;

/**
 * 拦截器，让用户自己定义需要在doHandler方法执行之前后的需要执行的个性化方法
 * @date 2019年9月20日  
 * @version 1.0  
 * @author shenhufei
 */
public interface Filter {
	/**
	 * 前置执行
	 * @author shenhufei
	 *
	 */
	void beforeDo(Object...obj);
	/**
	 * 后置执行
	 * @author shenhufei
	 *
	 */
	void afterDo(Object...obj);
	
	/**
	 * 前后执行执行
	 * @author shenhufei
	 *
	 */
	void aroundDo(Object...obj);
	
}
