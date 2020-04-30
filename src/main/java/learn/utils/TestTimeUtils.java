package learn.utils;

import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONArray;

public class TestTimeUtils {

	public static void main(String[] args) {
		TimeUnit days = TimeUnit.DAYS;
		long days2 = TimeUnit.MICROSECONDS.toNanos(10L);
		long days3 = TimeUnit.SECONDS.toDays(1000);
		System.out.println(JSONArray.toJSONString(days));
		System.out.println(JSONArray.toJSONString(days2));
		System.out.println(JSONArray.toJSONString(days3));
		
	}

}
