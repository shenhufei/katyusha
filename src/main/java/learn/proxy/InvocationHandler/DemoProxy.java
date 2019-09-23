package learn.proxy.InvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DemoProxy implements InvocationHandler{
	private Object target;
	 
    public DemoProxy(Object target) {
        this.target = target;
    }
 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行开始");
        Object obj = method.invoke(target,args);
        System.out.println("执行结束");
        return obj;
    }

}
