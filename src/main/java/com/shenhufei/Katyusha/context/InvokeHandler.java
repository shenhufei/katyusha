package com.shenhufei.Katyusha.context;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.ReflectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.model.RequestParam;

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
	public Object doHandler(RequestParam param) throws ClassNotFoundException {
		// 获取服务版本号
		String versionString = getMethodVersion(param.getVersion(),
				param.getCode());
		// 知道服务版本号，这个版本号就是我们在service层写的注解对应的版本号信息；
		// 需要去调用那个累的知道了code值就知道了那个接口；
		// TODO 修改这种创建对象的方式为spring的那种；
		/*
		 * WebApplicationContext wc = WebApplicationContextUtils
		 * .getRequiredWebApplicationContext(
		 * param.getInvocation().getServletContext());
		 */

		List<Methods> listMethod = VersionHepler.getListMethod();
		for (Methods methods : listMethod) {
			if (methods.getVersionMethodCode().equals(versionString)) {
				Object object = SpringContextUtil
						.getBean(methods.getClassName());
				Class<?> fullClassName = Class
						.forName(methods.getFullClassName());
				Class<?>[] parameterTypes = methods.getParameterTypes();

				Method method2 = ReflectionUtils.findMethod(fullClassName,
						methods.getMethodName(), parameterTypes);
				return ReflectionUtils.invokeMethod(method2, object,
						JSONArray.toJSONString(param.getObj()));
			}
		}
		return null;
	}

	public static String getMethodVersion(String version, Integer code) {
		Map<String, String> versionMap = VersionHepler.versionMap;
		Set<String> keySet = versionMap.keySet();
		String key = null;
		for (String string : keySet) {
			String[] split = string.split("_");
			Integer service = Integer.parseInt(split[0]);
			if (service.equals(code)) {
				key = string;
				break;
			}
		}
		String versionString = null;
		if (null != key) {
			versionString = versionMap.get(key);
		} else {
			return null;
		}
		if (null != versionString) {
			String[] split = versionString.split(",");
			String str = code + "_" + version;
			for (int i = 0; i < split.length; i++) {
				if (str.equals(split[i])) {
					return split[i];
				}
			}

		}
		return null;

	}
}
