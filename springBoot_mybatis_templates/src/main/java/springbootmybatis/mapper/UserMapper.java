package springbootmybatis.mapper;

import java.util.List;

import springbootmybatis.bean.User;

public interface UserMapper {

	List<User> getUserList();

	void add(User user);

}
