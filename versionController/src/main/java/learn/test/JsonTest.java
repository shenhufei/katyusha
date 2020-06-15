package learn.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

public class JsonTest {

	public static void main(String[] args) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(857, 11);
		map.put(288318, 22);
		map.put(288381, 22);
		map.put(675639, 22);
		System.out.println(JSONArray.toJSONString(map));
		
		String js = JSONArray.toJSONString(map);
		List<Map> parseArray = JSONArray.parseArray(js, Map.class);
		System.out.println(parseArray);
	}

}
