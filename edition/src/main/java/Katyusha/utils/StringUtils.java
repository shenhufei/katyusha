package Katyusha.utils;

import com.shenhufei.Katyusha.anntion.MethodVersion;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringUtils {
    public static void getMethodVersionCode(Map<String, String> versionMap,
            List<Annotation> transArrayToCollection, Integer code,String version) {
        MethodVersion versionAtnn = getMethodVersionAtnn(
                transArrayToCollection);
        // 拿到当前方法对应支持那些版本的代码
        String[] value = ((MethodVersion) versionAtnn).value();
        String str = "";
            if (null != value) {
                for (int i = 0; i < value.length; i++) {
                    str = str + code + "_" + value[i] + ",";
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
