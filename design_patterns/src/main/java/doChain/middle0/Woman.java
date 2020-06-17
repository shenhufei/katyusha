package doChain.middle0;

public class Woman implements PeopleChain{
    @Override
    public void doChain(String str) {
        System.out.println(str+"化妆");
    }
}
