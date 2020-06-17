package doChain.middle1;


public class BoyChain implements PeopleChain {
    @Override
    public void doChain(String str) {
        System.out.println(str +"锻炼身体");
    }
}
