package chain;

import java.util.List;

public class DataChain implements DataFilter {

    private List<DataFilter> dataFilters;

    @Override
    public void doFilter(DataRequest dataRequest, DataReponse dataReponse) {
        for(int i=0;i<dataFilters.size();i++) {
            dataFilters.get(i).doFilter(dataRequest, dataReponse);
        }

    }
    public List<DataFilter> getDataFilter() {
        return dataFilters;
    }

    public void setDataFilter(List<DataFilter> dataFilter) {
        this.dataFilters = dataFilter;
    }

}
