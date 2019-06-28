package com.shenhufei.Katyusha.context;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shenhufei.Katyusha.anntion.Version;
import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.utils.FileUtils;

/**
 * 类操作工具类
 *
 * @author shenhufei
 * @since 1.0.0
 */
@Component
public class VersionHepler2 implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(VersionHepler2.class);
    public static List<Methods> listMethod = new ArrayList<Methods>();
    /**
     * 请求的版本和响应版本的对应关系
     */
    public static Map<String, String> versionMap = new ConcurrentHashMap<>();
    public static List<String> list = new ArrayList<>();

    public static List<Methods> getListMethod() {
        return listMethod;
    }

    public static void setListMethod(List<Methods> listMethod) {
        VersionHepler2.listMethod = listMethod;
    }

    public static List<String> getList() {
        return list;
    }

    public static void setList(List<String> list) {
        VersionHepler2.list = list;
    }

    public static Map<String, String> getVersionMap() {
        return versionMap;
    }

    public static void setVersionMap(Map<String, String> versionMap) {
        VersionHepler2.versionMap = versionMap;
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

    public static List<AnnotatedType> transArrayToCollection(
            AnnotatedType[] anno) {
        List<AnnotatedType> list = new ArrayList<>();
        for (AnnotatedType annotatedType : anno) {
            list.add(annotatedType);
        }
        return list;
    }

    public static void init() throws Exception {
        List<Class<?>> list = FileUtils
                .getClassSet("com.ttpai.stock.biz.service.app");
        // TODO初始化一个接口名称和 code对应关系的集合；
        Map<String, Integer> map = InitMethodeNameAndCode();
        List<String> listString = getClassNameList();
        List<Method> listObjectMethods = arraytoArrayList();
        for (int i = 0; i < list.size(); i++) {
            // 有多少类被扫描到了就会有多少个VsersionControllerPojo对象
            Class<?> class1 = list.get(i);
            Annotation[] annotations = class1.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Version) {
                    // 获取当前类的名称
                    if (!listString.contains(class1.getName())) {

                        Method[] methods = class1.getMethods();
                        for (Method method : methods) {
                            // 去掉超级父类的方法；
                            if (!listObjectMethods.contains(method)) {
                                // 去掉中间非接口对应的方法
                                Integer code = map.get(method.getName());
                                /*
                                 * if (null == code) { //
                                 * 找不到需要抛出异常，而且需要处理那种并不是执行的接口 throw new
                                 * Exception(method.getName() +
                                 * "没有对应的code值，请检查初始化的InitMethodeNameAndCode方法")
                                 * ; }
                                 */
                                Methods methodss = getMethods(method, code,
                                        ((Version) annotation).value(),
                                        class1.getName());
                                listMethod.add(methodss);
                            }
                        }
                    }
                }
            }
        }
    }

    private static Methods getMethods(Method method, Integer code,
            String version, String className) {
        Methods methodss = new Methods();
        methodss.setVersionMethodCode(code + "_" + version);
        methodss.setVersion(version);
        methodss.setCode(code);
        String[] split = className.split("\\.");
        methodss.setClassName(getBeanName(split[split.length - 1]));
        methodss.setFullClassName(className);
        // 获取方法名称
        methodss.setMethodName(method.getName());
        // 获取方法返回值
        methodss.setReturnType(method.getReturnType());
        // 获取方法参数列表
        methodss.setParameters(method.getParameters());
        // 获取方法的参数的个数
        methodss.setParameterCount(method.getParameterCount());
        // 获取参数的类型的字节码文件
        methodss.setParameterTypes(method.getParameterTypes());
        return methodss;
    }

    private static String getBeanName(String string) {
        String charAt = (string.charAt(0) + "").toLowerCase();
        String substring = string.substring(1, string.length());
        return charAt + substring;
    }

    public static void main(String args[]) throws Exception {

        /*
         * String str = "com.ttpai.stock.biz.service.app.UserServiceImpl";
         * String[] split = str.split("\\."); String string = split[split.length
         * - 1]; String beanName = getBeanName(string);
         * System.out.println(beanName);
         */

        List<Class<?>> list = FileUtils
                .getClassSet("com.ttpai.stock.biz.service.app");
        // TODO初始化一个接口名称和 code对应关系的集合；
        Map<String, Integer> map = InitMethodeNameAndCode();
        List<String> listString = getClassNameList();
        List<Method> listObjectMethods = arraytoArrayList();
        for (int i = 0; i < list.size(); i++) {
            // 有多少类被扫描到了就会有多少个VsersionControllerPojo对象
            Class<?> class1 = list.get(i);
            Annotation[] annotations = class1.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Version) {
                    // 获取当前类的名称
                    if (!listString.contains(class1.getName())) {

                        Method[] methods = class1.getMethods();
                        // 如果没有方法数组的话
                        if (methods.length > 0) {
                            for (Method method : methods) {
                                // TODO 去掉超级父类的方法；
                                if (!listObjectMethods.contains(method)) {
                                    Integer code = map.get(method.getName());
                                    if (null == code) {
                                        // TODO 找不到需要抛出异常，而且需要处理那种并不是执行的接口

                                        /*
                                         * System.out.println(method.getName());
                                         * throw new Exception(
                                         * "方法名没有对应的code值，请检查初始化的InitMethodeNameAndCode方法"
                                         * );
                                         */
                                    }
                                    Methods methodss = getMethods(method, code,
                                            ((Version) annotation).value(),
                                            class1.getName());
                                    listMethod.add(methodss);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    private static Map<String, Integer> InitMethodeNameAndCode() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("login", 2000);
        map.put("logout", 2001);
        map.put("getStockMessage", 2002);
        map.put("getStockTaskStatistics", 3000);
        map.put("getEnterStockTaskList", 3010);
        map.put("getEnterStockTaskDetail", 3011);
        map.put("updateEnterStockStatus", 3012);
        map.put("getStockCheckTaskList", 3020);
        map.put("getStockCheckTaskDetail", 3021);
        map.put("updateCheckStockStatus", 3022);
        map.put("getLatestVersion", 100001);
        return map;
    }

    private static List<String> getClassNameList() {
        List<String> list = new ArrayList<String>();
        if (null != listMethod && listMethod.size() > 0) {
            for (Methods pojo : listMethod) {
                list.add(pojo.getClassName());
            }
        }
        return list;
    }

    /**
     * 将数组转成集合
     * 
     * @author shenhufei
     *
     * @return
     */
    private static List<Method> arraytoArrayList() {
        Method[] objectMethods = (new Object()).getClass().getMethods();
        List<Method> list = new ArrayList<Method>();
        for (Method method : objectMethods) {
            list.add(method);
        }
        return list;
    }

    /*
     * public static Object doHandler(BaseRequest requestParam) throws
     * ClassNotFoundException { // 获取服务版本号 String versionString =
     * getMethodVersion(requestParam.getVersion(), requestParam.getService());
     * // 知道服务版本号，这个版本号就是我们在service层写的注解对应的版本号信息； // 需要去调用那个累的知道了code值就知道了那个接口；
     * WebApplicationContext wc = WebApplicationContextUtils
     * .getRequiredWebApplicationContext(
     * requestParam.getInvocation().getServletContext()); for (Methods methods :
     * listMethod) { if (methods.getVersionMethodCode().equals(versionString)) {
     * Object object = wc.getBean(methods.getClassName()); Class<?>
     * fullClassName = Class .forName(methods.getFullClassName()); Class<?>[]
     * parameterTypes = methods.getParameterTypes();
     * 
     * Method method2 = ReflectionUtils.findMethod(fullClassName,
     * methods.getMethodName(), parameterTypes[0]); String jsonString =
     * JSONArray.toJSONString(
     * requestParam.getBaseRequestMessageDTO().getBody()); StockDetailRequest
     * request = JSONArray.parseObject(jsonString, StockDetailRequest.class); if
     * (null != request && null != request.getStock()) { return
     * ReflectionUtils.invokeMethod(method2, object, request);
     * 
     * } else { LoginRequest loginRequest = JSONArray .parseObject(jsonString,
     * LoginRequest.class); return ReflectionUtils.invokeMethod(method2, object,
     * loginRequest); } } } return null; }
     */

    public static String getMethodVersion(String version, Integer code) {
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

    @Override
    public void run(String... arg0) throws Exception {
        init();

    }

}
