package learn.proxy.jdk.InvocationHandler;

public class UserServiceImpl implements UserService{
	@Override
	public void print() {
		System.out.println("你大爷");	
	}

}
