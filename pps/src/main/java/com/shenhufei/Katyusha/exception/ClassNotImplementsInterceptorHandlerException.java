package com.shenhufei.Katyusha.exception;

/**
 * 初始化的时候某个类没有实现InterceptorHandler接口，但是其中的方法却加了前置，后置，环绕的注解
 * @date 2019年9月24日  
 * @version 1.0  
 * @author shenhufei
 */
public class ClassNotImplementsInterceptorHandlerException extends Exception{

	
	private static final long serialVersionUID = -3835892122917908320L;
	
	public ClassNotImplementsInterceptorHandlerException(Object... args){
		
	}
}
