package learn.Stringappend;

public class StringBuilderDemo {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		StringBuilder demo = new StringBuilder("");
		for (int i = 0; i <10000; i++) {
			demo.append(i);
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		//时间是1 毫秒
	}

}
