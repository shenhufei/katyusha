package com.shenhufei.Katyusha.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shenhufei.Katyusha.anntion.After;
import com.shenhufei.Katyusha.anntion.Around;
import com.shenhufei.Katyusha.anntion.Before;
import com.shenhufei.Katyusha.exception.ClassNotImplementsInterceptorHandlerException;
import com.shenhufei.Katyusha.filter.InterceptorHandler;
import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.utils.CollectionUtils;

/**
 * 前置，后置，环绕方法初始化类
 * @date 2019年9月25日  
 * @version 1.0  
 * @author shenhufei
 */
public  class BeforeAfterAroundMethods implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAfterAroundMethods.class);

	private CountDownLatch downLatch;

	public BeforeAfterAroundMethods(CountDownLatch downLatch) {
		this.downLatch = downLatch;
	}

	@Override
	public void run() {
		try {
			LOGGER.info("BeforeAfterAroundMethods init start");
			initBeforeAfterAroundMethods();
			LOGGER.info("BeforeAfterAroundMethods init end");
			LOGGER.info("init end");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.downLatch.countDown();
		}
	}
	public void initBeforeAfterAroundMethods() throws Exception {
		Collection<Methods> values = VersionHandler.mapMethod.values();
		List<Class<?>> list = VersionHandler.list;
		List<String> listString = VersionHandler.listString;
		LOGGER.info("BeforeAfterAroundMethods initing");
		for (Methods methods : values) {
			String fullClassName = methods.getFullClassName();
			Class<?> c = Class.forName(fullClassName);
			// 拿到该类的实现的接口；
			List<Class<?>> interfaceList = CollectionUtils
					.transArrayToCollection(c.getInterfaces());
			// 拿到所有的方法
			Method[] declaredMethods = c.getDeclaredMethods();
			// 遍历所有的方法;
			// 如果这个类实现了InterceptorHandler接口；
			if (interfaceList.contains(InterceptorHandler.class)) {

			} else {
				checkInterceptorHandler(declaredMethods, fullClassName);
			}

		}

	}

	/**
	 * 如果这个类没有实现这个接口，但是还是实现了这个里面的 前置，后置，环绕方法，那么就抛出异常
	 * 
	 * @author shenhufei
	 *
	 * @param declaredMethods
	 * @throws ClassNotImplementsInterceptorHandlerException
	 */
	private static void checkInterceptorHandler(Method[] declaredMethods,
			String fullClassName)
			throws ClassNotImplementsInterceptorHandlerException {
		for (Method method : declaredMethods) {
			List<Annotation> annotationList = CollectionUtils
					.transArrayToCollection(method.getAnnotations());
			for (Annotation annotation : annotationList) {
				if (annotation instanceof Before) {
					throw new ClassNotImplementsInterceptorHandlerException(
							fullClassName
									+ ":not Implements InterceptorHandler");
				}
				if (annotation instanceof After) {
					throw new ClassNotImplementsInterceptorHandlerException(
							fullClassName
									+ ":not Implements InterceptorHandler");
				}
				if (annotation instanceof Around) {
					throw new ClassNotImplementsInterceptorHandlerException(
							fullClassName
									+ ":not Implements InterceptorHandler");
				}
			}
		}

	}
}
