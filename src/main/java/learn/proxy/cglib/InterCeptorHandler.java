package learn.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class InterCeptorHandler implements MethodInterceptor{
	public Object getProxy(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        //设置回调方法
        enhancer.setCallback(this);
        return enhancer.create();
    }


	@Override
	public Object intercept(Object proxy, Method arg1, Object[] arg2,
			MethodProxy method) throws Throwable {
		System.out.println("执行开始");
        Object o1 = method.invokeSuper(proxy,arg2);
        System.out.println("执行结束");
        return o1;
	}

}
