package com.shenhufei.Katyusha.core;

import com.alibaba.fastjson.JSONArray;
import com.shenhufei.Katyusha.exception.MethodCodeNotFoundException;
import com.shenhufei.Katyusha.exception.MethodNotFoundException;
import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.model.Request;
import com.shenhufei.Katyusha.utils.MethodUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 真正去执行的类
 * 
 * @date 2019年9月20日
 * @version 1.0
 * @author shenhufei
 */
public class InvokeMethodHandler implements InvokeHandler {

	private static InvokeMethodHandler handler;
	/**
	 * 私有构造方法
	 */
	private InvokeMethodHandler() {
	}

	public static synchronized InvokeMethodHandler getInstance() {
		if (null == handler) {
			handler = new InvokeMethodHandler();
		}
		return handler;
	}
	@Override
	public Object doHandler(Request param) throws ClassNotFoundException, MethodNotFoundException, MethodCodeNotFoundException {
		//TODO  获取前置方法，获取后置方法；环绕方法，这个执行的方法执行的粒度是针对于类还是针对于方法；
		//TODO  添加自定义注解，注解用在类上面，表示这个

		// 需要扫描，用户是否是实现了Filter接口，看是否需要执行响应的方法；
		// 获取服务版本号
		String versionString = MethodUtils.getMethodVersion(VersionHandler.getVersionMap(), param.getVersion(), param.getCode());
		// 知道服务版本号，这个版本号就是我们在service层写的注解对应的版本号信息；
		// 需要去调用那个累的知道了code值就知道了那个接口；
		
		// 变成Map集合存储的形式会更好，效率会更高
		if(null==versionString){
			throw new MethodNotFoundException();
		}
		//此处完全是通过反射去执行代码的；
		Map<String, Methods> mapMethod = VersionHandler.getMapMethod();
		Methods methodsHandler = mapMethod.get(versionString);
		if(null==methodsHandler){
			throw new MethodCodeNotFoundException();
		}
		//TODO 需要改造成动态代理的方式去调用这个，摆脱参数类型传递的问题
		Object object = BeanFactory.getInstance().getBean(methodsHandler.getClassName());
		Class<?> fullClassName = Class.forName(methodsHandler.getFullClassName());
		Class<?>[] parameterTypes = methodsHandler.getParameterTypes();
		Method method2 = ReflectionUtils.findMethod(fullClassName,methodsHandler.getMethodName(), parameterTypes);
		return ReflectionUtils.invokeMethod(method2, object,JSONArray.toJSONString(param.getObj()));
	}

}
