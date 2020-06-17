package com.shenhufei.utils;

import com.shenhufei.base.DivideDBConfig;
import com.shenhufei.enums.DataBaseDataTypeEnum;
import com.shenhufei.enums.JavaBeanTypeEnum;
import com.shenhufei.pojo.DivideConfigPojo;
import com.shenhufei.pojo.PojoClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * @Description: 组装sql语句类
 * @author shenhufei  
 * @date 2019年4月30日 下午5:08:24 
 * @version V1.0   
 */
public class SqlBuildUtils {

    public static String getCreateTableSql(String tableName,
            HashMap<String, String> map, Integer part) {
        Iterator<Entry<String, String>> it = map.entrySet().iterator();
        String sql = "create table " + tableName + "(";
        while (it.hasNext()) {
            Entry<String, String> next = it.next();
            if (next.getValue().equals(JavaBeanTypeEnum.longE.getType()) || next
                    .getValue().equals(JavaBeanTypeEnum.LongE.getType())) {
                if (next.getKey().equals("id")) {
                    // TODO
                    sql = sql + next.getKey()
                            + " bigint NOT NULL  auto_increment PRIMARY KEY,";
                } else {
                    sql = sql + next.getKey() + " bigint ,";
                }
                continue;

            }
            if (next.getValue().equals(JavaBeanTypeEnum.doubleE.getType())
                    || next.getValue()
                            .equals(JavaBeanTypeEnum.DoubleE.getType())) {
                sql = sql + next.getKey() + " "
                        + DataBaseDataTypeEnum.DECIMAL.getType() + ",";
                continue;
            }
            if (next.getValue().equals(JavaBeanTypeEnum.floatE.getType())
                    || next.getValue()
                            .equals(JavaBeanTypeEnum.FloatE.getType())) {
                sql = sql + next.getKey() + " "
                        + DataBaseDataTypeEnum.DECIMAL.getType() + ",";
                continue;
            }
            if (next.getValue().equals(JavaBeanTypeEnum.inte.getType()) || next
                    .getValue().equals(JavaBeanTypeEnum.IntegerE.getType())) {
                sql = sql + next.getKey() + " "
                        + JavaBeanTypeEnum.inte.getType() + "  ,";
                continue;
            }
            if (next.getValue().equals(JavaBeanTypeEnum.shortE.getType())
                    || next.getValue()
                            .equals(JavaBeanTypeEnum.ShortE.getType())) {
                sql = sql + next.getKey() + " "
                        + JavaBeanTypeEnum.inte.getType() + "  ,";
                continue;
            }
            if (next.getValue().equals(JavaBeanTypeEnum.bytes.getType()) || next
                    .getValue().equals(JavaBeanTypeEnum.Bytes.getType())) {
                sql = sql + next.getKey() + " "
                        + JavaBeanTypeEnum.tinyint.getType() + "  ,";
                continue;
            }
            if (next.getValue().equals(JavaBeanTypeEnum.booleanE.getType())
                    || next.getValue()
                            .equals(JavaBeanTypeEnum.BooleanE.getType())) {
                sql = sql + next.getKey() + " "
                        + DataBaseDataTypeEnum.BITE.getType() + "  ,";
                continue;
            }
            if (next.getValue().equals(JavaBeanTypeEnum.stringE.getType())) {
                sql = sql + next.getKey() + "  varchar(64),";
                continue;
            }
            if (next.getValue().equals(JavaBeanTypeEnum.DateE.getType())) {
                sql = sql + next.getKey() + " "
                        + DataBaseDataTypeEnum.DateE.getType() + ",";
                continue;
            }
        }
        return sql.substring(0, sql.length() - 1) + ")";
    }

    public static String getInsertConfigTableSql(String pojoName,
                                                 DivideConfigPojo config, String tableName) {

        String configTableName = DivideDBConfig.DIVIDECONFIGTableName;
        String sql = "insert into " + configTableName
                + "(table_name,pojo_name,part,step,des)values('" + tableName
                + "','" + pojoName + "'," + config.getPart() + ",20,'"
                + pojoName + "  divide table config'  )";
        return sql;
    }

    public static String getSqlByFields(Class<? extends Object> obj) {

        return null;
    }

    /**
     * 根据字节码文件，Object对象，表名组装insert语句
     * 
     * @author shenhufei
     *
     * @param obj
     * @param o
     * @param tablename
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static String getInsertSqlByField(Class<? extends Object> obj,
            Object o, String tablename)
            throws IllegalArgumentException, IllegalAccessException {

        String sql = "insert into " + tablename + "(";
        Field[] fields = obj.getDeclaredFields();
        List<Field> listField = getNoIgnoreFieldList(fields);
        return getValueString(listField, sql, o);
    }

    /**
     * 拿到inset语句，一步一步的拼接，insert 表名 在添加中间的字段() 再根据顺序添加字段对应的值，字符串类型和时间类型的数据需要特别注意；
     * 
     * @author shenhufei
     *
     * @param listField
     * @param sql
     * @param o
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static String getValueString(List<Field> listField, String sql,
            Object o) throws IllegalArgumentException, IllegalAccessException {
        List<PojoClass> list = new ArrayList<PojoClass>();
        for (int i = 0; i < listField.size(); i++) {
            listField.get(i).setAccessible(true);
            if (i == listField.size() - 1) {
                sql = sql + listField.get(i).getName() + ") values(";
                list.add(getPojoClass(listField.get(i), o));
            } else {
                if (!listField.get(i).getName().equals("id")) {
                    sql = sql + listField.get(i).getName() + ",";
                    list.add(getPojoClass(listField.get(i), o));
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sql = StringUtils.getValueString1(list.get(i), sql);

            } else {
                sql = StringUtils.getValueString2(list.get(i), sql);
            }
        }
        return sql;
    }

    /**
     * 拿到没有被@ignore修饰的字段，获取数据库表对应的所有实体类字段
     * 
     * @author shenhufei
     *
     * @param fields
     * @return
     */
    private static List<Field> getNoIgnoreFieldList(Field[] fields) {
        List<Field> listField = new ArrayList<Field>();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations.length == 0) {
                listField.add(field);
            }
        }
        return listField;
    }

    /**
     * 获取每个字段对应的字段名，数据类型，值
     * 
     * @author shenhufei
     *
     * @param field
     * @param o
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static PojoClass getPojoClass(Field field, Object o)
            throws IllegalArgumentException, IllegalAccessException {
        PojoClass pojoClass = new PojoClass();
        pojoClass.setName(field.getName());
        pojoClass.setType(field.getType());
        pojoClass.setValue(field.get(o));
        return pojoClass;
    }

    /**
     * 获取分表的步长的sql语句
     * 
     * @author shenhufei
     *
     * @param part
     * @return
     */
    public static String getAutoIncrementSql(Integer part) {
        return "set @@auto_increment_increment= " + part + ";";
    }

}
