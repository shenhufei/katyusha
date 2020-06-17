package com.shenhufei.utils;

import java.util.regex.Pattern;

import com.shenhufei.base.DivideDBConfig;
import com.shenhufei.pojo.PojoClass;

public class StringUtils {
    public static String getFieldDataType(String fieldType) {
        String fieldT = null;
        if (fieldType.equals("java.lang.Long") || fieldType.equals("long")) {
            fieldT = "Long";
        }
        if (fieldType.equals("java.lang.Integer") || fieldType.equals("int")) {
            fieldT = "Integer";
        }
        if (fieldType.equals("java.lang.Double")
                || fieldType.equals("double")) {
            fieldT = "Double";
        }
        if (fieldType.equals("java.lang.Float") || fieldType.equals("float")) {
            fieldT = "Float";
        }
        if (fieldType.equals("java.lang.String")) {
            fieldT = "String";
        }
        if (fieldType.equals("java.lang.Short") || fieldType.equals("short")) {
            fieldT = "Short";
        }
        if (fieldType.equals("java.lang.Byte") || fieldType.equals("byte")) {
            fieldT = "Byte";
        }
        if (fieldType.equals("char")) {
            fieldT = "char";
        }
        if (fieldType.equals("java.lang.Boolean")
                || fieldType.equals("boolean")) {
            fieldT = "Boolean";
        }
        if (fieldType.equals("java.util.Date")) {
            fieldT = "Date";
        }
        return fieldT;
    }

    public static Boolean getIfValueString(String fieldType) {
        String[] split = fieldType.split(" ");
        if (split.length == 2) {
            if (split[1].equals("java.lang.String")
                    || split[1].equals("java.util.Date")) {
                return true;
            }
        } else {
            if (fieldType.equals("java.lang.String")
                    || fieldType.equals("java.util.Date")) {
                return true;
            }
        }

        return false;

    }

    public static String getTableName(String pojoName) {
        String regEx = DivideDBConfig.tableNameRegEx;
        Pattern p = Pattern.compile(regEx);
        /* 按照句子结束符分割句子 */
        String[] substrs = p.split(pojoName);
        return substrs[substrs.length - 1].toLowerCase();
    }

    public static String getValueString1(PojoClass pojoClass, String sql) {
        if (StringUtils.getIfValueString(pojoClass.getType().toString())) {
            if (null == pojoClass.getValue()) {
                sql = sql + "null" + ")";
            } else {
                sql = sql + "'" + pojoClass.getValue() + "'" + ")";
            }

        } else {
            sql = sql + pojoClass.getValue() + ")";
        }
        return sql;
    }

    public static String getValueString2(PojoClass pojoClass, String sql) {
        if (StringUtils.getIfValueString(pojoClass.getType().toString())) {
            if (null == pojoClass.getValue()) {
                sql = sql + "null" + ",";
            } else {
                sql = sql + "'" + pojoClass.getValue() + "'" + ",";
            }

        } else {
            sql = sql + pojoClass.getValue() + ",";
        }
        return sql;
    }

}
