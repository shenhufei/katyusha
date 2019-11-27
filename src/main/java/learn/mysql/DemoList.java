package learn.mysql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DemoList {

	public static void main(String[] args) {
		String s = "766,1019,205831,691547,678780,749446,690246,727097,677272,"+
    "725645,674916,689705,717905,740515,739599,671354,687369,713540,736625,733609";
		String[] split = s.split(",");
		System.out.println(split.length);
		List<String> resultList= new ArrayList<>(Arrays.asList(split));
		
		Collections.sort(resultList);
		System.out.println(resultList);
		
		

	}

}
