package com.shenhufei.Katyusha.utils;

import java.util.Map;
import java.util.Set;

public class MethodUtils {
	public static String getMethodVersion(Map<String, String> versionMap, String version, Integer code) {
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
}
