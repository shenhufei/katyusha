package chain;

public class NBDataFilter implements DataFilter {


    @Override
    public void doFilter(DataRequest dataRequest, DataReponse dataReponse) {
        System.out.println("dataReponse:nb进来了");
        System.out.println("dataRequest:nb进来了");
    }

}
