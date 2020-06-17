package doChain.high;

public class Man implements PeopleChain{
    @Override
    public Boolean doChain(String str) {
        System.out.print(str+"运动");
        return false;
    }
}
