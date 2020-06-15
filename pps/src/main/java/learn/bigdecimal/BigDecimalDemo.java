package learn.bigdecimal;

import java.math.BigDecimal;

public class BigDecimalDemo {

	public static void main(String[] args) {
		Integer i = 100;
		BigDecimal bigDecimal = new BigDecimal(i.toString());
		System.out.println(bigDecimal.toString());
		
		
		
		double value1=1.2345678912311;
        double value2=9.1234567890123;
        BigDecimal value3=new BigDecimal(Double.toString(value1));
        BigDecimal value4=new BigDecimal(Double.toString(value2));
        System.out.println(value3.toString());
        System.out.println(value4.toString());
	}

}
