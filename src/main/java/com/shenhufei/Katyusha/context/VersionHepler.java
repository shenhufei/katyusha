package com.shenhufei.Katyusha.context;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.shenhufei.Katyusha.anntion.Code;
import com.shenhufei.Katyusha.anntion.Igonre;
import com.shenhufei.Katyusha.anntion.MethodVersion;
import com.shenhufei.Katyusha.anntion.Version;
import com.shenhufei.Katyusha.model.Methods;
import com.shenhufei.Katyusha.utils.CollectionUtils;

/**
 * 类操作工具类
 *
 * @author shenhufei
 * @since 1.0.0
 */
@Component
public class VersionHepler implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(VersionHepler.class);
    public static List<Methods> listMethod = new ArrayList<Methods>();
    static List<Method> listObjectMethods = arraytoArrayList();
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

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 加载类（默认将初始化类）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 获取指定包名下的所有类
     */
    public static List<Class<?>> getClassSet(String packageName) {
        List<Class<?>> classSet = new ArrayList<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader()
                    .getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20",
                                " ");
                        addClass(classSet, packagePath, packageName);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url
                                .openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile
                                        .entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries
                                            .nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName
                                                .substring(0, jarEntryName
                                                        .lastIndexOf("."))
                                                .replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addClass(List<Class<?>> classSet, String packagePath,
            String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class"))
                        || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0,
                        fileName.lastIndexOf("."));
                if (!"".equals(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (!"".equals(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (!"".equals(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(List<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }

    // TODO
    public static void init() throws Exception {
        List<Class<?>> list = getVersionListClass(
                getClassSet("com.ttpai.stock.biz.service.app"));
        // TODO初始化一个接口名称和 code对应关系的集合；
        List<String> listString = getClassNameList();
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
                        List<Method> listMethods = getClassMethod(
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
                            if (!hasIgoneAton(transArrayToCollection)) {
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
    }

    private static List<Class<?>> getVersionListClass(
            List<Class<?>> listClass) {
        List<Class<?>> result = new ArrayList<Class<?>>();
        for (Class<?> class1 : listClass) {
            List<Annotation> list = CollectionUtils
                    .transArrayToCollection(class1.getAnnotations());
            if (null != list && list.size() > 0) {
                for (Annotation annotation : list) {
                    if (annotation instanceof Version) {
                        result.add(class1);
                    }
                }
            }
        }
        return result;
    }

    private static boolean hasIgoneAton(
            List<Annotation> transArrayToCollection) {
        for (Annotation annotation : transArrayToCollection) {
            if (annotation instanceof Igonre) {
                return true;
            }
        }
        return false;
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
        List<Class<?>> list = getVersionListClass(
                getClassSet("com.ttpai.stock.biz.service.app"));
        // TODO初始化一个接口名称和 code对应关系的集合；
        List<String> listString = getClassNameList();
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
                        List<Method> listMethods = getClassMethod(
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
                            if (!hasIgoneAton(transArrayToCollection)) {
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
    }

    private static void doInit(List<Annotation> transArrayToCollection,
            Method method, Annotation annotation, Class<?> class1)
            throws Exception {
        Code codeAnnotation = getCodeAtnn(transArrayToCollection);
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
        getMethodVersionCode(transArrayToCollection, value,
                ((Version) annotation).value());
        Methods methodss = getMethods(method, value,
                ((Version) annotation).value(), class1.getName());
        listMethod.add(methodss);

    }

    private static void getMethodVersionCode(
            List<Annotation> transArrayToCollection, Integer code,
            String version) {
        MethodVersion versionAtnn = getMethodVersionAtnn(
                transArrayToCollection);
        // 拿到当前方法对应支持那些版本的代码
        String[] value = ((MethodVersion) versionAtnn).value();
        String str = "";
        if (null != value) {
            String[] split = value[0].split("\\,");
            if (null != split) {
                for (int i = 0; i < split.length; i++) {
                    str = str + code + "_" + split[i] + ",";
                }
                versionMap.put(code + "_" + version, str);
            }
        }
    }

    private static MethodVersion getMethodVersionAtnn(
            List<Annotation> transArrayToCollection) {
        for (Annotation annotation : transArrayToCollection) {
            if (annotation instanceof MethodVersion) {
                return (MethodVersion) annotation;
            }
        }
        return null;
    }

    private static Code getCodeAtnn(List<Annotation> transArrayToCollection) {
        for (Annotation annotation : transArrayToCollection) {
            if (annotation instanceof Code) {
                return (Code) annotation;
            }
        }
        return null;
    }

    private static List<Method> getClassMethod(Method[] methods) {
        List<Method> arrayList = new ArrayList<Method>();
        for (Method method : methods) {
            if (!listObjectMethods.contains(method))
                arrayList.add(method);
        }
        return arrayList;
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
    public void afterPropertiesSet() throws Exception {
        init();

    }

}
