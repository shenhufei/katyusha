package com.shenhufei.Katyusha.utils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
