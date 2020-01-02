package learn.filter;

import com.shenhufei.Katyusha.filter.Interceptor;

public class JudgeClassIsInterface {
	public static void main(String[] args) {
		//判断一个类是否实现了某个接口
		TestDemo demo = new TestDemo();
		if(demo instanceof Interceptor){
			System.out.println("11ss");
		}
	}
}
