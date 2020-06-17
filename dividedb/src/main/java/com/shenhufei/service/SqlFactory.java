package com.shenhufei.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.shenhufei.db.DBconnection;
import com.shenhufei.exception.PojoClassNotFoundException;
import com.shenhufei.pojo.DivideTableManagerConfigPojo;
import com.shenhufei.pojo.User;
import com.shenhufei.utils.SqlBuildUtils;
import com.shenhufei.utils.StringUtils;

/**
 *   
 * 
 * @Description: 具体来承担crud操作的类；
 * @author shenhufei  
 * @date 2019年5月1日 下午21:34:07 
 * @version V1.0   
 */
public class SqlFactory {
    // 最重要的是插入方法的，在插入之前就需要知道插入时候这个id是多少，需要分表配置管理表，
    // 以及分表配置的日志表；

    private static final Map<String, Integer> map = new HashMap<>();

    // 最重要的是插入方法的，在插入之前就需要知道插入时候这个id是多少，需要分表配置管理表，
    // 以及分表配置的日志表；
    public static <T> Boolean insert(T t) throws Exception {
        Class<? extends Object> obj = t.getClass();
        if (null == obj) {
            throw new PojoClassNotFoundException(
                    t.toString() + " can not found");
        }
        String className = obj.getName();
        // 需要去分表信息管理表当中读取分表信息表这个全路径对应的信息；
        try {
            DivideTableManagerConfigPojo config = DBconnection
                    .getDivideTableConfig(className);
            if (null == map.get(config.getPojoName())) {
                // 需要定位到哪一张表
                // 项目在初始化的时候由于不知道上一次插入的是哪一个分表当中，
                // 所以首次插入的时候需要做根据分表的个数随机，到哪一张表当中去；
                int part = getPartNum(config);
                map.put(config.getPojoName(), part);
                // 做插入操作的时候，需要组装成insert 语句，由于传递进来的对象是泛型，所以需要反射遍历对应的字段，
                doInsertSqlByField(obj, t, part);
            } else {
                // 获取上一次插入的是哪一张表，拿到对应表的下角标信息，角标信息+1 如果没有超过最大分表个数的时候
                // 那么角标+1得到的就是本次插入时候映射到的那张表，如果角标+1>最大分表个数，那么本次插入的表的角标就是1；

                int part = map.get(config.getPojoName()) + 1 > config.getPart()
                        ? 1 : map.get(config.getPojoName()) + 1;
                map.put(config.getPojoName(), part);
                doInsertSqlByField(obj, t, part);
            }
            return true;
        } catch (Exception e) {
            throw e;
        }

    }

    private static int getPartNum(DivideTableManagerConfigPojo config) {
        Random random = new Random();
        int part = random.nextInt(config.getPart() + 1);
        while (part == 0) {
            part = random.nextInt(config.getPart() + 1);
        }
        return part;
    }

    private static <T> void doInsertSqlByField(Class<? extends Object> obj, T t,
            int part) throws Exception {
        String table = StringUtils.getTableName(obj.getName()) + "_" + part;

        String string = SqlBuildUtils.getInsertSqlByField(obj, t, table);
        // 并且拿到每个字段对应的数值才行；
        DBconnection.insertDivideTable(string);

    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 90; i++) {
            User use = new User();
            // use.setA(1);
            // use.setB(2);
            // use.setB(3);
            /*
             * use.setBytes(new Byte("2")); use.setBytess((byte) 3);
             * use.setC((float) 6.0); use.setChars((char) 3); use.setD((float)
             * 4.9);
             */
            // use.setG(4L);

            // use.setId(3L);
            // use.setJ(true);
            use.setK(true);
            // use.setMoney(4.5);
            // use.setMoneys(4.6);
            // use.setP(new Date());
            // use.setShorts((short) 3.2);
            // use.setShortSs((short) 3.5);
            use.setAge(20);
            use.setName("樊哙");
            use.setTest("test");

            insert(use);
        }

    }

}
