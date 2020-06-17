package chain;

public class OneDataFilter implements DataFilter{
        @Override
    public void doFilter(DataRequest dataRequest,DataReponse dataReponse) {
            System.out.println("dataRequest:nb进来了");
            System.out.println("dataReponse:nb进来了");
        }
}
