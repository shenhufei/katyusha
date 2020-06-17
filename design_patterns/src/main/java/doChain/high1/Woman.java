package doChain.high1;

public class Woman implements PeopleChain {
    @Override
    public Boolean doChain(Request req, Response res) {
        System.out.println(req.getRequest()+"化妆");
        System.out.println(res.getRes()+"化妆2");
        return  true;
    }
}
