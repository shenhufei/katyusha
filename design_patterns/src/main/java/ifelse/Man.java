package ifelse;

import doChain.base.PeopleChain;

public class Man implements PeopleMethodInterface {
    @Override
    public void doMethod() {
        System.out.println("Man doSomethings");
    }
}
