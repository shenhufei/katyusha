package learn.test;

import com.shenhufei.Katyusha.anntion.Before;
import com.shenhufei.Katyusha.filter.InterceptorHandler;

public class DemoTest implements InterceptorHandler{
	@Before("learn.test.impl.AppDemo2ServiceImpl")
	public void testBefore(){
		System.out.println("前置方法开始执行了");
	}
}
