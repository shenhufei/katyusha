package doChain.base;

import doChain.base.PeopleChain;

public class Woman implements PeopleChain {
    @Override
    public void doSomething(String str) {
        System.out.println(str+"化妆");
    }
}
