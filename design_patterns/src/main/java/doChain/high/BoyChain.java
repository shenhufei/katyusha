package doChain.high;



public class BoyChain implements PeopleChain {
    @Override
    public Boolean doChain(String str) {
        System.out.println(str +"锻炼身体");
        return false;
    }
}
