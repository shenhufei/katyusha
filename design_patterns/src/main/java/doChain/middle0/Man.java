package doChain.middle0;

public class Man implements PeopleChain{
    @Override
    public void doChain(String str) {
        System.out.print(str+"运动");
    }
}
