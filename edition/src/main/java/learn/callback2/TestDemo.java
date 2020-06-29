package learn.callback2;

public class TestDemo {
	public static void main(String[] args) {
		Worker worker = new Worker();
		Boss boss = new Boss();
		boss.tell();
		//获取当前时间毫秒值
		long currentTimeMillis = System.currentTimeMillis();
		
	}
}
