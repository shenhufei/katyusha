package chain;
public class UppFilter implements DataFilter{

    @Override
    public void doFilter(DataRequest dataRequest,DataReponse dataReponse) {
        System.out.println("dataRequest:udp进来了");
        System.out.println("dataReponse:udp进来了");
    }
}
