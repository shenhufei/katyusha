package learn.memory;

import java.util.ArrayList;

/**
 * @author shenhufei
 * @version 1.0
 * @Description:o
 * @date 20200623
 */
public class TestMemory {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();
        for(;;){
            Person person = new Person();
            person.setAge(10L);
            person.setName("dasdasdasdas");
            list.add(person);
        }
    }
}
