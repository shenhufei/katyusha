package learn.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;

public class FlightTicketInfo {
	private String orderNumber;
	 
    private String userName;
 
    private String age;
 
    public FlightTicketInfo(String orderNumber, String userName, String age) {
        this.orderNumber = orderNumber;
        this.userName = userName;
        this.age = age;
    }
 
    @Override
    public String toString() {
        return "FlightTicketInfo{" +
                "orderNumber='" + orderNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
 
    public String getOrderNumber() {
        return orderNumber;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public String getAge() {
        return age;
    }
    
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
    	List<FlightTicketInfo> infoList = new ArrayList<>();
        infoList.add(new FlightTicketInfo("11111", "赵", "22"));
        infoList.add(new FlightTicketInfo("22222", "钱", "22"));
        infoList.add(new FlightTicketInfo("33333", "孙", "23"));
        infoList.add(new FlightTicketInfo("11111", "李", "22"));
        infoList.add(new FlightTicketInfo("11111", "李", "29"));
        infoList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> 
                new TreeSet<>(Comparator.comparing(info -> info.getUserName()+info.getAge()))), ArrayList::new))
                .forEach(System.out::println);
        
        ArrayList<FlightTicketInfo> collect = infoList.stream()
        .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> 
        new TreeSet<>(Comparator.comparing(info -> info.getUserName()+info.getAge()))), ArrayList::new));
        System.out.println("长度是："+collect.size()+"；数据是："+JSONArray.toJSONString(collect));

	}
}
