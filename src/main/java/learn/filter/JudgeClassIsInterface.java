package learn.filter;

import com.shenhufei.Katyusha.filter.InterceptorHandler;

public class JudgeClassIsInterface {
	public static void main(String[] args) {
		//判断一个类是否实现了某个接口
		TestDemo demo = new TestDemo();
		if(demo instanceof InterceptorHandler){
			System.out.println("11ss");
		}
	}
}
