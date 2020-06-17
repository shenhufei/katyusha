package doChain.high1;

public class Man implements PeopleChain {
    @Override
    public Boolean doChain(Request req, Response res) {
        System.out.print(req.getRequest()+"运动");
        System.out.print(res.getRes()+"运动2");
        return false;
    }
}
