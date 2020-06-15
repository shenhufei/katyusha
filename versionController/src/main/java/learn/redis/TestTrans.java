package learn.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author shenhufei  测试redis的jedis形式的事物
 * @version 1.0
 * @Description:
 * @date 20200615
 */
public class TestTrans {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","lq");
        jsonObject.put("age",23);

        //开启事务
        Transaction transaction =jedis.multi();

        String result = jsonObject.toJSONString();

        //jedis.watch(result);

        try{
            transaction.set("user3",result);
            transaction.set("user4",result);
            ///int a = 1/0;
            //执行事务
            transaction.exec();
        }catch (Exception e){

            //放弃事务
            transaction.discard();
            e.printStackTrace();
        }finally {
            System.out.println(jedis.get("user3"));
            System.out.println(jedis.get("user4"));
            //关闭连接
            jedis.close();
        }
    }
}
