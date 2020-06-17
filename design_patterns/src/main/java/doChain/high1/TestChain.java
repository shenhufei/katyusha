package doChain.high1;


public class TestChain {
    public static void main(String[] args) {
        FilterChain chain = new FilterChain();
        Man man = new Man();
        Woman woman = new Woman();
        chain.addChain(man).addChain(woman);
        FilterChain chain2 = new FilterChain();
        BoyChain boyChain = new BoyChain();

        chain2.addChain(chain).addChain(boyChain);
        Request req = new Request();
        req.setRequest("请求参数");
        Response res =new Response();
        res.setRes("相应参数");
        chain2.doChain(req,res);
    }
}
