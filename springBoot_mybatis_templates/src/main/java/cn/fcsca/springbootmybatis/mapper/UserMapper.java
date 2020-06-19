package cn.fcsca.springbootmybatis.mapper;

import java.util.List;

import cn.fcsca.springbootmybatis.bean.User;

public interface UserMapper {

	List<User> getUserList();

	void add(User user);

}
