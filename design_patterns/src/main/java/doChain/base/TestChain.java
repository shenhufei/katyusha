package doChain.base;

import java.util.ArrayList;
import java.util.List;

public class TestChain {
    public static void main(String[] args) {
        List<PeopleChain> list = new ArrayList<>();
        Man man = new Man();
        Woman woman = new Woman();
        list.add(man);
        list.add(woman);
        String str = "开始";
        for(int i=0;i<list.size();i++) {
            list.get(i).doSomething(str);
        }
    }
}
