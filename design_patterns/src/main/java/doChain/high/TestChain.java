package doChain.high;



public class TestChain {
    public static void main(String[] args) {
        FilterChain chain = new FilterChain();
        Man man = new Man();
        Woman woman = new Woman();
        chain.addChain(man).addChain(woman);
        FilterChain chain2 = new FilterChain();
        BoyChain boyChain = new BoyChain();

        chain2.addChain(chain).addChain(boyChain);

        chain2.doChain("开始");
    }
}
