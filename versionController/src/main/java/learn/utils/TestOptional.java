package learn.utils;

import java.util.Optional;

public class TestOptional {

	public static void main(String[] args) {
		Optional<String> opt  = Optional.of("");
		if(opt.isPresent()){
			System.out.println("asdasd");
		}
		
		Optional<String> opt2  = Optional.ofNullable(null);
		if(opt2.isPresent()){
			System.out.println("asdasd");
		}else{
			System.out.println("false");
		}
	}

}
