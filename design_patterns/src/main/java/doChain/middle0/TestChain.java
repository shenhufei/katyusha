package doChain.middle0;


public class TestChain {
    public static void main(String[] args) {
        FilterChain chain = new FilterChain();
        Man man = new Man();
        Woman woman = new Woman();
        chain.addChain(man).addChain(woman);
        chain.doChain("开始");
    }
}
