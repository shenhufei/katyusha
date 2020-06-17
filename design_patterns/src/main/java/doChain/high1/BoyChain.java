package doChain.high1;


public class BoyChain implements PeopleChain {
    @Override
    public Boolean doChain(Request req, Response res) {
        System.out.println(req.getRequest() +"锻炼身体");
        System.out.println(res.getRes() +"锻炼身体2");
        return false;
    }
}
