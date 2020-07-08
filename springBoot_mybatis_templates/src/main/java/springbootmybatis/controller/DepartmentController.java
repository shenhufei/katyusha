package springbootmybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootmybatis.bean.User;
import springbootmybatis.mapper.UserMapper;

/**
 * DepartmentController
 *
 * @author Fcscanf@樊乘乘
 * @description
 * @date 下午 15:38 2018-08-14
 */
@RestController
public class DepartmentController {

    @Autowired
    UserMapper userMapper;

    
    
    @GetMapping("/list")
    public List<User> getUserList() {
    	System.out.println("---------------");
        return userMapper.getUserList();
    }
    
    @PutMapping("/add")
    public void add() {
    	System.out.println("---------------");
    	User user = new User();
    	user.setAge(1);
    	user.setName("大哥");
         userMapper.add(user);
    }
    
}
