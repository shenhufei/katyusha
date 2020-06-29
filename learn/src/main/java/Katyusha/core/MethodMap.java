package Katyusha.core;

import com.shenhufei.Katyusha.anntion.Code;
import com.shenhufei.Katyusha.anntion.Version;
import com.shenhufei.Katyusha.exception.MethodCodeNotFoundException;
import com.shenhufei.Katyusha.exception.MethodCodeValueNotFoundException;
import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.utils.CollectionUtils;
import com.shenhufei.Katyusha.utils.DataUtils;
import com.shenhufei.Katyusha.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 版本控制方法执行初始化类
 * @date 2019年9月25日  
 * @version 1.0  
 * @author shenhufei
 */
public  class MethodMap implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodMap.class);

	private CountDownLatch downLatch;

	public MethodMap(CountDownLatch downLatch) {
		this.downLatch = downLatch;
	}

	@Override
	public void run() {
		//  需要加自己的数据
		List<Class<?>> list = VersionHandler.list;
		List<String> listString = VersionHandler.getListString();
		try {
			LOGGER.info("MethodMap init start");
			initMethodMap(list,listString);
			LOGGER.info("MethodMap init end");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.downLatch.countDown();
		}
	}
	
	public void initMethodMap(List<Class<?>> list, List<String> listString)throws Exception {
		LOGGER.info("MethodMap initing");
		// TODO 需要把这个写死的路径，修改成配置或者。
		// TODO 方式1：步骤1.可以使用properties 文件，也可以使用xml配置；还必须是可以在多个配置任意以properties
		// ，xml 文件格式的配置文件找那个书写
		// 2.但是必须是这些配置文件中有且仅有一个配置，否则抛出配置多余的异常提示用户；
		// TODO 方式2 1.或者检查有无类实现了 PathHandler 这个接口，拿到这个接口的实现类中的path路径。
		for (Class<?> class1 : list) {
			// 有多少类被扫描到了就会有多少个VsersionControllerPojo对象
			Annotation[] annotations = class1.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Version) {
					// 将所有的类对应的版本信息都录入到集合当中，最后再去重筛选，拿到去重后的版本信息；
					// listVersion 把该集合去重；
					CollectionUtils.add(VersionHandler.listVersion,
							((Version) annotation).value());
					// 从已经添加到了Map集合中的方法的类，再次拿来做校验，防止在方法Map集合中添加重复的方法
					if (!listString.contains(class1.getName())) {
						// 去掉超级父类的方法；
						List<Method> listMethods = CollectionUtils
								.getClassMethod(
										VersionHandler.listObjectMethods,
										class1.getMethods());
						for (Method method : listMethods) {
							Annotation[] annotations2 = method.getAnnotations();
							if (annotations2.length == 0) {
								continue;
							}
							// 去掉igonre 注解的方法，igonre注解的方法是这个类中不是版本控制的方法
							List<Annotation> transArrayToCollection = CollectionUtils
									.transArrayToCollection(annotations2);
							if (!CollectionUtils
									.hasIgoneAton(transArrayToCollection)) {
								// 再去手机方法上面的兼容的版本信息；
								// 去掉中间非接口对应的方法
								doMethodMap(transArrayToCollection, method,
										annotation, class1);
							}
						}
					}
				}
			}
		}
		
	}

	private static void doMethodMap(List<Annotation> transArrayToCollection,
			Method method, Annotation annotation, Class<?> class1)
			throws Exception {
		Code codeAnnotation = CollectionUtils
				.getCodeAtnn(transArrayToCollection);
		if (null == codeAnnotation) {
			throw new MethodCodeNotFoundException(
					method.getName() + "方法是否添加了code注解");
		}
		Integer value = codeAnnotation.value();
		if (value.equals(-1)) {
			// 找不到需要抛出异常，而且需要处理那种并不是执行的接口 throw new
			throw new MethodCodeValueNotFoundException(
					method.getName() + "方法对应的code注解没有对应的code值");
		}
		// 将接口名和对应的code值进行存储
		VersionHandler.map.put(method.getName(), value);
		// 记录方法和其对应的支持的那些版本的服务进行记录
		StringUtils.getMethodVersionCode(VersionHandler.versionMap,
				transArrayToCollection, value, ((Version) annotation).value());
		Methods methodss = DataUtils.getMethods(method, value,
				((Version) annotation).value(), class1.getName());
		// 这个位置使用Map集合来接受 ,handler中查询这个方法的时候速度会更快
		VersionHandler.mapMethod.put(methodss.getVersionMethodCode(), methodss);

	}

}
