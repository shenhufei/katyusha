package learn.test.impl;

import com.shenhufei.Katyusha.anntion.Before;
import com.shenhufei.Katyusha.filter.Interceptor;

public class DemoTest implements Interceptor{
	@Before("learn.test.impl.AppDemo2ServiceImpl")
	public void testBefore(){
		System.out.println("前置方法开始执行了");
	}
}
