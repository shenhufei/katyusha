package learn.json;

import java.util.Date;

import com.alibaba.fastjson.JSONArray;

public class DateJsonTest {

	public static void main(String[] args) {
		DemoPojo pojo = new  DemoPojo();
		pojo.setDate(new Date());
		pojo.setName("你大爷");
		String string = JSONArray.toJSONString(pojo);
		System.out.println(string);
		//{"date":1577088509087,"name":"你大爷"}
		
		DemoPojo pojo2 = new  DemoPojo();
		pojo2.setDate(new Date());
		pojo2.setName("你大爷");
		String string2 = JSONArray.toJSONString(pojo2);
		System.out.println(string2);

	}

}
