package com.shenhufei.Katyusha.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.utils.CollectionUtils;
import com.shenhufei.Katyusha.utils.FileUtils;

/**
 * 类操作工具类
 *
 * @author shenhufei
 * @since 1.0.0
 */
public abstract class VersionHandler implements VersionInit, InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(VersionHandler.class);

	/**
	 * 扫描的字节码文件
	 */
	public static List<Class<?>> list;

	public static List<String> listString;
	public static List<String> getListString() {
		return listString;
	}

	public static void setListString(List<String> listString) {
		VersionHandler.listString = listString;
	}

	public static Map<String, Methods> mapMethod = new HashMap<String, Methods>();
	/**
	 * 获取超级父类中的所有方法，准备在后续操作中，将这些过滤掉
	 */
	static List<Method> listObjectMethods = CollectionUtils.arraytoArrayList();

	/**
	 * 存储接口名，接口code码
	 */
	static Map<String, Integer> map = new HashMap<>();
	public static Map<String, String> versionMap = new HashMap<>();
	/**
	 * 请求的版本和响应版本的对应关系
	 */
	public static List<String> listVersion = new ArrayList<>();

	public static Map<String, Methods> getMapMethod() {
		return mapMethod;
	}

	public static void setListMethod(Map<String, Methods> mapMethod) {
		VersionHandler.mapMethod = mapMethod;
	}

	public static List<String> getList() {
		return listVersion;
	}

	public static void setList(List<String> list) {
		VersionHandler.listVersion = list;
	}

	public static Map<String, String> getVersionMap() {
		return versionMap;
	}

	public static void setVersionMap(Map<String, String> versionMap) {
		VersionHandler.versionMap = versionMap;
	}

	/**
	 * 根据版本号获得对应处理类 当没有对应处理类时，默认向前找最新的处理类
	 *
	 * @param version
	 * @return
	 */
	public static String getHandler(String version, Integer code) {
		return versionMap.get(code + "_" + version);

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		list = CollectionUtils.getVersionListClass(
				FileUtils.getClassSet("learn.test.impl"));
		// TODO初始化一个接口名称和 code对应关系的集合；
		listString = CollectionUtils.getClassNameList(mapMethod);
		ExecutorService executor = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(2);
		MethodMap m1 = new MethodMap(latch);
		BeforeAfterAroundMethods m2 = new BeforeAfterAroundMethods(latch);
		executor.execute(m1);
		executor.execute(m2);
		executor.shutdown();
	}
	
	
	//TODO 此处需要将测试的main方法全部去掉
	public static void main(String[] args) {
		//获取一个接口下所有的实现类
		Reflections reflections = new Reflections("learn.test");
        Set<Class<? extends PathHandler>> classes = reflections.getSubTypesOf(PathHandler.class);
        for(Class<?> clazz : classes) {
            System.out.println("Found: " + clazz.getName());
        }
		LOGGER.info("init start");
		list = CollectionUtils.getVersionListClass(FileUtils.getClassSet("learn.test.impl"));
		// TODO初始化一个接口名称和 code对应关系的集合；
		listString = CollectionUtils.getClassNameList(mapMethod);
		ExecutorService executor = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(2);
		MethodMap task1 = new MethodMap(latch);
		BeforeAfterAroundMethods task2 = new BeforeAfterAroundMethods(latch);
		executor.execute(task1);
		executor.execute(task2);
		executor.shutdown();
	}

}
