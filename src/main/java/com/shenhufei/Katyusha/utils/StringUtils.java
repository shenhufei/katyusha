package com.shenhufei.Katyusha.utils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shenhufei.Katyusha.anntion.MethodVersion;

public class StringUtils {
    public static void getMethodVersionCode(Map<String, String> versionMap,
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

    public static String getMethodVersion(Map<String, String> versionMap,
            String version, Integer code) {
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

    public static String getBeanName(String string) {
        String charAt = (string.charAt(0) + "").toLowerCase();
        String substring = string.substring(1, string.length());
        return charAt + substring;
    }

}
