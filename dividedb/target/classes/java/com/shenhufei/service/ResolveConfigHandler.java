package com.shenhufei.service;

import com.shenhufei.db.DBconnection;
import com.shenhufei.pojo.DivideConfigPojo;
import com.shenhufei.utils.SqlBuildUtils;
import com.shenhufei.utils.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Description: 将存储在集合中的分表配置信息转存到对应的实体类当中去；
 * @author shenhufei  
 * @date 2019年4月30日 下午3:09:46 
 * @version V1.0   
 */
public class ResolveConfigHandler implements InitializingBean{
	@Override
	public void afterPropertiesSet() throws Exception {
		getAllTable();
	}
    public static void getAllTable() throws Exception, DocumentException {
        Map<String, HashMap<String, String>> map = ReadDivideConfig
                .resolveConfigList();
        // 拿到所有的实体类全路径;
        // Set<String> set = map.keySet();
        Iterator<Entry<String, HashMap<String, String>>> it = map.entrySet()
                .iterator();
        List<DivideConfigPojo> config = ReadDivideConfig.getListConfig();
        while (it.hasNext()) {
            Entry<String, HashMap<String, String>> next = it.next();
            // 实体类的路径
            String pojoName = next.getKey();
            DivideConfigPojo pojo = getPojoConfig(config, pojoName);
            // 实体类对应表的分表配置
            HashMap<String, String> mapValue = next.getValue();
            Integer part = pojo.getPart();

            for (int i = 0; i < part; i++) {
                // 需要对这个pojoName这个值做切割；
                String tableName = StringUtils.getTableName(pojoName) + "_"
                        + (i + 1);
                // 这个表在数据库里面是否存在；
                Boolean exist = DBconnection.getTableExist(tableName);
                // 存在就不管，不存在就需要手动去创建表；
                if (!exist) {
                    String createsql = SqlBuildUtils.getCreateTableSql(tableName,
                            mapValue, i + 1);
                    // 这个位置需要放开
                    String autoIncrementSql = SqlBuildUtils
                            .getAutoIncrementSql(part);
                    DBconnection.createTable(createsql, autoIncrementSql, i + 1,
                            tableName);
                    // 需要新增表
                    // 新增的表创建之后需要在一个分表的注册表当中去新增自己的数据；
                    String sqlInsert = SqlBuildUtils
                            .getInsertConfigTableSql(pojoName, pojo, tableName);
                    DBconnection.insertDivideTable(sqlInsert);
                }
            }
        }
    }

    private static DivideConfigPojo getPojoConfig(List<DivideConfigPojo> config,
                                                  String pojoName) {
        for (DivideConfigPojo divideConfigPojo : config) {
            if (divideConfigPojo.getPojoName().equals(pojoName)) {
                return divideConfigPojo;
            }
        }
        return null;
    }

    public static void main(String[] args) throws DocumentException, Exception {
        getAllTable();
    }
}
