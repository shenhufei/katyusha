package learn.proxy.cglib;

import learn.proxy.jdk.InvocationHandler.UserServiceImpl;

public class CgLibDemo1 {

	public static void main(String[] args) {
		 InterCeptorHandler interCeptorHandler = new InterCeptorHandler();
		 UserServiceImpl userService = (UserServiceImpl) interCeptorHandler.getProxy(UserServiceImpl.class);
		 userService.print();

	}

}
