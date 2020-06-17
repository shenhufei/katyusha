package com.shenhufei.base;

public class DivideDBConfig {
    /**
     * 配置文件路径
     */
    public static final String CONFIGPATH = "src/main/resources/divide.xml";
    /**
     * 分表配置表表名
     */
    public static final String DIVIDECONFIGTableName = "divide_table_manager_config";
    /**
     * 数据库对应的java实体类的全路径进行分割时候的分割配置字符串；
     */
    public static final String tableNameRegEx = "[.]";

    /**
     * 忽略字段的的枚举类全路径
     */
    public static final String IGONRECLASS = "public abstract @interface com.ttpc.divideDB.annotations.ignore";

}
