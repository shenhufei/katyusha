package doChain.high;

public class Woman implements PeopleChain{
    @Override
    public Boolean doChain(String str) {
        System.out.println(str+"化妆");
        return  true;
    }
}
