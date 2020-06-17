package doChain.base;

public class Man implements PeopleChain {
    @Override
    public void doSomething(String str) {
        System.out.print(str+"运动");
    }
}
