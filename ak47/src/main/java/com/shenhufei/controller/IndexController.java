package com.shenhufei.controller;
import com.shenhufei.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:
 * @date 20200615
 */
@RestController
public class IndexController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/setString")
    public String setString(String key, String value){
        redisService.set(key, value, 5000l); //超时时间5000s   l表示long型
        return "成功";
    }

    @RequestMapping("get")
    public String get(String key){
        return redisService.getString(key);
    }
}