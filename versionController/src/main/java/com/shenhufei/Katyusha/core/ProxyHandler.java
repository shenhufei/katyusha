package com.shenhufei.Katyusha.core;

public class ProxyHandler extends ProxyFactory{

	public ProxyHandler(Object target) {
		super(target);
	}
	public Object doProxyMethod(Object  obj){
		ProxyFactory factory=new ProxyFactory(obj);
		Object user = (Object)factory.getProxyObject();
		//此处的问题是，传递进来的对象，又是一个不明确的Obejct对象，
		//TODO 需要传递进来一个明确的对象，
		//TODO 一个明确需要执行的方法；
		//TODO 方法的参数列表；
		//UserServiceImpl serviceImpl = new UserServiceImpl();
		//ProxyFactory factory=new ProxyFactory(serviceImpl);
		//UserService user = (UserService)factory.getProxyObject();
		//user.print();
		return null;
	}

}
