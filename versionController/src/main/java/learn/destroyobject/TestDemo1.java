package learn.destroyobject;

import java.util.ArrayList;
import java.util.List;

public class TestDemo1 {

	public static void main(String[] args) {
       List<Object> list = new ArrayList<Object>();
       list.add("12");
       list.add("13");
       list.add("14");
       list = null;
       System.out.println(list.size());
    }

	public String doSomething(){
	return 	this.doSomethings();
	}

	private String doSomethings() {
		return "asdasdsa";
		
	}
}

