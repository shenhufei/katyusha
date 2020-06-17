package chain;

import java.util.ArrayList;
import java.util.List;

public class DataHandleTest {
    public static void main(String[] args) {
        DataChain dataChain = new DataChain();
        List<DataFilter> dataFilters = new ArrayList<>();
        dataFilters.add(new NBDataFilter());
        dataFilters.add(new UppFilter());
        dataFilters.add(new OneDataFilter());
        dataChain.setDataFilter(dataFilters);
        dataChain.doFilter(new DataRequest(), new DataReponse());
    }

}
