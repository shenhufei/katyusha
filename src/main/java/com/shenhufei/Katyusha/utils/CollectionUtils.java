package com.shenhufei.Katyusha.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.shenhufei.Katyusha.anntion.Code;
import com.shenhufei.Katyusha.anntion.Igonre;
import com.shenhufei.Katyusha.anntion.Version;
import com.shenhufei.Katyusha.model.Methods;

/**
 * 集合相关工具类
 * 
 * @author shenhufei
 */
public class CollectionUtils {
    public static List<Annotation> transArrayToCollection(Annotation[] anno) {
        List<Annotation> list = new ArrayList<>();
        for (Annotation annotatedType : anno) {
            list.add(annotatedType);
        }
        return list;
    }

    public static void pastLeep(List<String> listVersion) {
        List<String> listNew = new ArrayList<>();
        Set<String> set = new HashSet<String>();
        set.addAll(listVersion);
        listNew.addAll(set);
    }

    public static void add(List<String> listVersion, String value) {
        if (!listVersion.contains(value)) {
            listVersion.add(value);
        }

    }

    /**
     * 将数组转成集合
     * 
     * @author shenhufei
     *
     * @return
     */
    public static List<Method> arraytoArrayList() {
        Method[] objectMethods = (new Object()).getClass().getMethods();
        List<Method> list = new ArrayList<Method>();
        for (Method method : objectMethods) {
            list.add(method);
        }
        return list;
    }

    public static List<Method> getClassMethod(List<Method> listObjectMethods,
            Method[] methods) {
        List<Method> arrayList = new ArrayList<Method>();
        for (Method method : methods) {
            if (!listObjectMethods.contains(method))
                arrayList.add(method);
        }
        return arrayList;
    }

    public static List<Class<?>> getVersionListClass(List<Class<?>> listClass) {
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

    public static Code getCodeAtnn(List<Annotation> transArrayToCollection) {
        for (Annotation annotation : transArrayToCollection) {
            if (annotation instanceof Code) {
                return (Code) annotation;
            }
        }
        return null;
    }

    public static List<String> getClassNameList(List<Methods> listMethod) {
        List<String> list = new ArrayList<String>();
        if (null != listMethod && listMethod.size() > 0) {
            for (Methods pojo : listMethod) {
                list.add(pojo.getClassName());
            }
        }
        return list;
    }

    public static boolean hasIgoneAton(
            List<Annotation> transArrayToCollection) {
        for (Annotation annotation : transArrayToCollection) {
            if (annotation instanceof Igonre) {
                return true;
            }
        }
        return false;
    }
    
    
}
