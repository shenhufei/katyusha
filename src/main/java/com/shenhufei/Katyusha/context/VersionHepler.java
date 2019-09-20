package com.shenhufei.Katyusha.context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.shenhufei.Katyusha.anntion.Code;
import com.shenhufei.Katyusha.anntion.Version;
import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.utils.CollectionUtils;
import com.shenhufei.Katyusha.utils.DataUtils;
import com.shenhufei.Katyusha.utils.FileUtils;
import com.shenhufei.Katyusha.utils.StringUtils;

/**
 * 类操作工具类
 *
 * @author shenhufei
 * @since 1.0.0
 */
public class VersionHepler implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(VersionHepler.class);
    public static List<Methods> listMethod = new ArrayList<Methods>();
    static List<Method> listObjectMethods = CollectionUtils.arraytoArrayList();
    static Map<String, Integer> map = new HashMap<>();
    static Map<String, String> versionMap = new HashMap<>();
    /**
     * 请求的版本和响应版本的对应关系
     */
    public static List<String> listVersion = new ArrayList<>();

    public static List<Methods> getListMethod() {
        return listMethod;
    }

    public static void setListMethod(List<Methods> listMethod) {
        VersionHepler.listMethod = listMethod;
    }

    public static List<String> getList() {
        return listVersion;
    }

    public static void setList(List<String> list) {
        VersionHepler.listVersion = list;
    }

    public static Map<String, String> getVersionMap() {
        return versionMap;
    }

    public static void setVersionMap(Map<String, String> versionMap) {
        VersionHepler.versionMap = versionMap;
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

    /**
     * 该方法变成那种根据version,code,可以获取到这个版本的方法对应的服务版本号；
     * 
     * @author shenhufei
     *
     * @param version
     * @param code
     * @return
     */

    // TODO
    public static void init() throws Exception {
    	LOGGER.info("init starting");
        List<Class<?>> list = CollectionUtils.getVersionListClass(
                FileUtils.getClassSet("com.ttpai.stock.biz.service.app"));
        // TODO初始化一个接口名称和 code对应关系的集合；
        List<String> listString = CollectionUtils.getClassNameList(listMethod);
        for (int i = 0; i < list.size(); i++) {
            // 有多少类被扫描到了就会有多少个VsersionControllerPojo对象
            Class<?> class1 = list.get(i);
            Annotation[] annotations = class1.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Version) {
                    // 将所有的类对应的版本信息都录入到集合当中，最后再去重筛选，拿到去重后的版本信息；
                    // listVersion 把该集合去重；
                    CollectionUtils.add(listVersion,
                            ((Version) annotation).value());
                    // 获取当前类的名称
                    if (!listString.contains(class1.getName())) {
                        // 去掉超级父类的方法；
                        List<Method> listMethods = CollectionUtils
                                .getClassMethod(listObjectMethods,
                                        class1.getMethods());
                        for (Method method : listMethods) {
                            Annotation[] annotations2 = method.getAnnotations();
                            if (annotations2.length == 0) {
                                System.out.println(method.getName());
                                continue;
                            }
                            // 判断是否含有igonre注解；
                            List<Annotation> transArrayToCollection = CollectionUtils
                                    .transArrayToCollection(annotations2);
                            if (!CollectionUtils
                                    .hasIgoneAton(transArrayToCollection)) {
                                // 再去手机方法上面的兼容的版本信息；
                                // 去掉中间非接口对应的方法
                                doInit(transArrayToCollection, method,
                                        annotation, class1);
                            }
                        }
                    }
                }
            }
        }
        LOGGER.info("init end");
    }

    private static void doInit(List<Annotation> transArrayToCollection,
            Method method, Annotation annotation, Class<?> class1)
            throws Exception {
        Code codeAnnotation = CollectionUtils
                .getCodeAtnn(transArrayToCollection);
        if (null == codeAnnotation) {
            throw new Exception(method.getName() + "方法是否添加了code注解");
        }
        Integer value = codeAnnotation.value();
        if (value.equals(-1)) {
            // 找不到需要抛出异常，而且需要处理那种并不是执行的接口 throw new
            throw new Exception(method.getName() + "方法对应的code注解没有对应的code值");
        }
        // 将接口名和对应的code值进行存储
        map.put(method.getName(), value);
        // 记录方法和其对应的支持的那些版本的服务进行记录
        StringUtils.getMethodVersionCode(versionMap, transArrayToCollection,
                value, ((Version) annotation).value());
        Methods methodss = DataUtils.getMethods(method, value,
                ((Version) annotation).value(), class1.getName());
        listMethod.add(methodss);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

}
