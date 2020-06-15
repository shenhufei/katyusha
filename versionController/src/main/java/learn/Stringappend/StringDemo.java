package learn.Stringappend;

public class StringDemo {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String demo = "";
		for (int i = 0; i <10000; i++) {
			demo = demo+i;
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		// 207 毫秒
		
	}

}
