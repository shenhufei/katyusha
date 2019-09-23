package com.shenhufei.Katyusha.core;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.util.ReflectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.model.Request;
import com.shenhufei.Katyusha.utils.MethodUtils;

/**
 * 真正去执行的类
 * @date 2019年9月20日  
 * @version 1.0  
 * @author shenhufei
 */
public class InvokeHandler implements Handler {

	private static InvokeHandler handler;
	/**
	 * 私有构造方法
	 */
	private InvokeHandler() {
	}

	public static synchronized InvokeHandler getInstance() {
		if (null == handler) {
			handler = new InvokeHandler();
		}
		return handler;
	}
	public Object doHandler(Request param) throws ClassNotFoundException {
		//TODO 需要扫描，用户是否是实现了Filter接口，看是否需要执行响应的方法；
		// 获取服务版本号
		String versionString = MethodUtils.getMethodVersion(VersionHandler.versionMap,param.getVersion(),param.getCode());
		// 知道服务版本号，这个版本号就是我们在service层写的注解对应的版本号信息；
		// 需要去调用那个累的知道了code值就知道了那个接口；
		
		/*
		 * WebApplicationContext wc = WebApplicationContextUtils
		 * .getRequiredWebApplicationContext(
		 * param.getInvocation().getServletContext());
		 */

		List<Methods> listMethod = VersionHandler.getListMethod();
		for (Methods methods : listMethod) {
			if (methods.getVersionMethodCode().equals(versionString)) {
				Object object = BeanFactory.getBean(methods.getClassName());
				Class<?> fullClassName = Class.forName(methods.getFullClassName());
				Class<?>[] parameterTypes = methods.getParameterTypes();
				Method method2 = ReflectionUtils.findMethod(fullClassName,
						methods.getMethodName(), parameterTypes);
				return ReflectionUtils.invokeMethod(method2, object,JSONArray.toJSONString(param.getObj()));
			}
		}
		return null;
	}

	
}
