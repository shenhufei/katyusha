package com.shenhufei.pojo;

import lombok.Data;

/**
 *   
 * 
 * @Description: 单例设计模式，启动时候读取数据库配置的时候封装数据库配置的实体类；
 * @author shenhufei  
 * @date 2019年5月1日 下午21:34:07 
 * @version V1.0   
 */
@Data
public class DataBaseConfigPojo {
    // 私有构造
    private DataBaseConfigPojo() {
    }

    private static DataBaseConfigPojo dbConfig = null;

    public static synchronized DataBaseConfigPojo getInstance() {
        if (dbConfig == null) {
            dbConfig = new DataBaseConfigPojo();
        }
        return dbConfig;
    }

    private String url;

    private String user;

    private String password;

    private String driver;

    // TODO 后期会加入其它更多关于数据库的配置信息；

}
